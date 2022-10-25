package com.evereats.fooder.api.controller;

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

    @GetMapping("/withFreeFreight")
    public List<Restaurant> restaurantsWithFreeFreight(@RequestParam("name") String name) {
        return restaurantService.restaurantsWithFreeFreight(name);
    }

    @GetMapping("/first")
    public Optional<Restaurant> findFirst() {
        return restaurantService.findFirst();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @PutMapping
    public Restaurant update(@RequestBody Restaurant restaurant) {
        return restaurantService.update(restaurant);
    }

    @PatchMapping("/{restaurantId}")
    public Restaurant partialUpdate(@PathVariable("restaurantId") Long id,
            @RequestBody Map<String, Object> fields) {
        return restaurantService.partialUpdate(id, fields);
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurantId") Long id) {
        restaurantService.delete(id);
    }
}
