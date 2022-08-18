package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.service.RestaurantRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRegisterService.save(restaurant));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantRegisterService.update(restaurant));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
