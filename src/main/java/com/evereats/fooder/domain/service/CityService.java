package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.CityNotFoundException;
import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.repository.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private static final String CITY_IN_USE = "Cidade de código %d não pode ser removido pois está em uso";
    private final CityRepository cityRepository;
    private final StateService stateService;

    public CityService(CityRepository cityRepository, StateService stateService) {
        this.cityRepository = cityRepository;
        this.stateService = stateService;
    }

    public List<City> list() {
        return cityRepository.findAll();
    }

    public City find(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        return city;
    }

    public City save(City city) {
        city.setState(stateService.find(city.getState().getId()));
        return cityRepository.save(city);
    }

    public City update(Long cityID, City city) {
        City currentCity = find(cityID);
        city.setState(stateService.find(city.getState().getId()));
        BeanUtils.copyProperties(city, currentCity, "id");

        return cityRepository.save(currentCity);
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(CITY_IN_USE, id));
        }
    }
}
