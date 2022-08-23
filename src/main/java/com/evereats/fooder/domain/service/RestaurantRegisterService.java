package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.KitchenRepository;
import com.evereats.fooder.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantRegisterService {

    private RestaurantRepository restaurantRepository;
    private KitchenRepository kitchenRepository;

    public RestaurantRegisterService(RestaurantRepository restaurantRepository, KitchenRepository kitchenRepository) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRepository = kitchenRepository;
    }

    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    public Restaurant find(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        if (restaurant == null) {
            throw new EntityNotFoundException(String.format("Não existe um registro de Restaurante de código %d", id));
        }

        return restaurant;
    }

    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = kitchenRepository.findById(restaurant.getKitchen().getId());

        if (kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Não existe um registro de Cozinha de código %d", restaurant.getKitchen().getId()));
        }

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant currentRestaurant = find(restaurant.getId());
        Kitchen kitchen = kitchenRepository.findById(restaurant.getKitchen().getId());

        if (kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Não existe um registro de Cozinha de código %d", restaurant.getKitchen().getId()));
        }

        BeanUtils.copyProperties(restaurant, currentRestaurant);
        return restaurantRepository.save(currentRestaurant);
    }

    public Restaurant partialUpdate(Long id, Map<String, Object> fields) {
        Restaurant restaurant = restaurantRepository.findById(id);

        if (restaurant == null) {
            throw new EntityNotFoundException(String.format("Não existe um registro de Restaurante de código", id));
        }

        merge(fields, restaurant);
        return update(restaurant);
    }

    private void merge(Map<String, Object> fields, Restaurant destiny) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant origin = objectMapper.convertValue(fields, Restaurant.class);

        fields.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, name);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, origin);
            ReflectionUtils.setField(field, destiny, newValue);
        });
    }

    public void delete(Long id) {
        try {
            restaurantRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um registro de Restaurante de código %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de código %d não pode ser removido, pois está em uso", id));
        }
    }
}
