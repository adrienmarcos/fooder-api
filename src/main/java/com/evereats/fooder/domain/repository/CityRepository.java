package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> list();
    City findById(Long id);
    City save(City city);
    void delete(Long id);
}
