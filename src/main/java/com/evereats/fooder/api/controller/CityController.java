package com.evereats.fooder.api.controller;

import com.evereats.fooder.api.exceptionHandler.ApiError;
import com.evereats.fooder.domain.exception.DomainException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.exception.StateNotFoundException;
import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> list() {
        return cityService.list();
    }

    @GetMapping("/{cityId}")
    public City find(@PathVariable("cityId") Long id) {
        return cityService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City save(@RequestBody City city) {
        try {
            return cityService.save(city);
        } catch(StateNotFoundException e) {
            throw new DomainException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityID}")
    public City update(@PathVariable(name = "cityID") Long cityID, @RequestBody City city) {
        try {
            return cityService.update(cityID, city);
        } catch(StateNotFoundException e) {
            throw new DomainException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("cityId") Long id) {
        cityService.delete(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        var apiError = ApiError.builder().dateTime(LocalDateTime.now()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException e) {
        var apiError = ApiError.builder().dateTime(LocalDateTime.now()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
