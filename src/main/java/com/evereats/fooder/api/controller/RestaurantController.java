package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.DomainException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.exception.KitchenNotFoundException;
import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> list() {
        return restaurantService.list();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant find(@PathVariable("restaurantId") Long id) {
        return restaurantService.find(id);
    }

    @GetMapping("/byFreightTax")
    public List<Restaurant> findByFreightTax(@RequestParam("initialFreightTax") BigDecimal initialFreightTax,
            @RequestParam("finalFreightTax") BigDecimal finalFreightTax) {
        return restaurantService.findByFreightTax(initialFreightTax, finalFreightTax);
    }

    @GetMapping("/byNameAndKitchen")
    public List<Restaurant> findByNameAndKitchen(@RequestParam("name") String name,
            @RequestParam("kitchenId") Long kitchenId) {
        return restaurantService.findByNameAndKitchen(name, kitchenId);
    }

    @GetMapping("/firstByName")
    public Optional<Restaurant> findFirstByName(@RequestParam("name") String name) {
        return restaurantService.findFirstByName(name);
    }

    @GetMapping("/firstTwoByName")
    public List<Restaurant> findFirstTwoByName(@RequestParam("name") String name) {
        return restaurantService.findFistTwoByName(name);
    }

    @GetMapping("/countByKitchen")
    public int countByKitchen(@RequestParam("kitchenId") Long kitchenId) {
        return restaurantService.countByKitchen(kitchenId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch(KitchenNotFoundException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantID}")
    public Restaurant update(@PathVariable(name = "restaurantID") long restaurantID, @RequestBody Restaurant restaurant) {
        try {
            return restaurantService.update(restaurantID, restaurant);
        } catch(KitchenNotFoundException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantID}")
    public Restaurant partialUpdate(@PathVariable("restaurantID") Long restaurantID,
            @RequestBody Map<String, Object> fields) {
        try {
            return restaurantService.partialUpdate(restaurantID, fields);
        } catch(EntityNotFoundException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurantId") Long id) {
        restaurantService.delete(id);
    }
}
