package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.list());
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> find(@PathVariable("cityId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.find(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody City city) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.update(city));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("cityId") Long id) {
        cityService.delete(id);
    }
}
