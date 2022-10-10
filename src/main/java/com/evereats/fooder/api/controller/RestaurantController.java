package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.service.RestaurantRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantRegisterService restaurantRegisterService;

    public RestaurantController(RestaurantRegisterService restaurantRegisterService) {
        this.restaurantRegisterService = restaurantRegisterService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantRegisterService.list());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> find(@PathVariable("restaurantId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantRegisterService.find(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/byFreightTax")
    public List<Restaurant> findByFreightTax(@RequestParam("initialFreightTax") BigDecimal initialFreightTax,
            @RequestParam("finalFreightTax") BigDecimal finalFreightTax) {
        return restaurantRegisterService.findByFreightTax(initialFreightTax, finalFreightTax);
    }

    @GetMapping("/byNameAndKitchen")
    public List<Restaurant> findByNameAndKitchen(@RequestParam("name") String name, @RequestParam("kitchenId") Long kitchenId) {
        return restaurantRegisterService.findByNameAndKitchen(name, kitchenId);
    }

    @GetMapping("/firstByName")
    public Optional<Restaurant> findFirstByName(@RequestParam("name") String name) {
        return restaurantRegisterService.findFirstByName(name);
    }

    @GetMapping("/firstTwoByName")
    public List<Restaurant> findFirstTwoByName(@RequestParam("name") String name) {
        return restaurantRegisterService.findFistTwoByName(name);
    }

    @GetMapping("/countByKitchen")
    public int countByKitchen(@RequestParam("kitchenId") Long kitchenId) {
        return restaurantRegisterService.countByKitchen(kitchenId);
    }

    @GetMapping("/withFreeFreight")
    public List<Restaurant> restaurantsWithFreeFreight(@RequestParam("name") String name) {
        return restaurantRegisterService.restaurantsWithFreeFreight(name);
    }

    @GetMapping("/first")
    public Optional<Restaurant> findFirst() {
        return restaurantRegisterService.findFirst();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRegisterService.save(restaurant));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantRegisterService.update(restaurant));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable("restaurantId") Long id,
            @RequestBody Map<String, Object> fields) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantRegisterService.partialUpdate(id, fields));
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> delete(@PathVariable("restaurantId") Long id) {
        try {
            restaurantRegisterService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
