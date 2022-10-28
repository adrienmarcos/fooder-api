package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.RestaurantNotFoundException;
import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestaurantService {

    private static final String RESTAURANT_IN_USE = "Restaurante de código %d não pode ser removido, pois está em uso";
    private final RestaurantRepository restaurantRepository;
    private final KitchenService kitchenService;

    public RestaurantService(RestaurantRepository restaurantRepository, KitchenService kitchenService) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenService = kitchenService;
    }

    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    public Restaurant find(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurant;
    }

    public List<Restaurant> findByFreightTax(BigDecimal initialFreightTax, BigDecimal finalFreightTax) {
        return restaurantRepository.findByFreightTaxBetween(initialFreightTax, finalFreightTax);
    }

    public List<Restaurant> findByNameAndKitchen(String name, Long kitchenId) {
        return restaurantRepository.findByNameAndKitchen(name, kitchenId);
    }

    public Optional<Restaurant> findFirstByName(String name) {
        return restaurantRepository.findFirstByNameContaining(name);
    }

    public List<Restaurant> findFistTwoByName(String name) {
        return restaurantRepository.findTop2ByNameContaining(name);
    }

    public int countByKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    public Restaurant save(Restaurant restaurant) {
        restaurant.setKitchen(kitchenService.find(restaurant.getKitchen().getId()));
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(long restaurantID, Restaurant restaurant) {
        Restaurant currentRestaurant = find(restaurantID);
        restaurant.setKitchen(kitchenService.find(restaurant.getKitchen().getId()));
        BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "address", "registerDate");

        return restaurantRepository.save(currentRestaurant);
    }

    public Restaurant partialUpdate(Long restaurantID, Map<String, Object> fields, HttpServletRequest request) {
        Restaurant restaurant = find(restaurantID);
        merge(fields, restaurant, request);

        return update(restaurantID, restaurant);
    }

    private void merge(Map<String, Object> fields, Restaurant destiny, HttpServletRequest request) {
        var serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            Restaurant origin = objectMapper.convertValue(fields, Restaurant.class);

            fields.forEach((name, value) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, name);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, origin);
                ReflectionUtils.setField(field, destiny, newValue);
            });
        } catch (IllegalArgumentException e) {
            throw new HttpMessageNotReadableException(e.getMessage(), ExceptionUtils.getRootCause(e), serverHttpRequest);
        }
    }

    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(RESTAURANT_IN_USE, id));
        }
    }
}
