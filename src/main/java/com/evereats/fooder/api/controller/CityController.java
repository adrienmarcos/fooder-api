package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.DomainException;
import com.evereats.fooder.domain.exception.StateNotFoundException;
import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public City save(@RequestBody @Valid City city) {
        try {
            return cityService.save(city);
        } catch(StateNotFoundException e) {
            throw new DomainException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityID}")
    public City update(@PathVariable(name = "cityID") Long cityID, @RequestBody @Valid City city) {
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
}