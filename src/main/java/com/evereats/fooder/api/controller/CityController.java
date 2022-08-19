package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.service.CityRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private CityRegisterService cityRegisterService;

    public CityController(CityRegisterService cityRegisterService) {
        this.cityRegisterService = cityRegisterService;
    }

    @GetMapping
    public ResponseEntity<List<City>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(cityRegisterService.list());
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> find(@PathVariable("cityId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityRegisterService.find(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cityRegisterService.save(city));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody City city) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityRegisterService.update(city));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> delete(@PathVariable("cityId") Long id) {
        try {
            cityRegisterService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
