package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.model.State;
import com.evereats.fooder.domain.repository.CityRepository;
import com.evereats.fooder.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public CityService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public List<City> list() {
        return cityRepository.findAll();
    }

    public City find(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um registro de Cidade de código %d", id)));

        return city;
    }

    public City save(City city) {
        State state = stateRepository.findById(city.getState().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um registro de Estado de código %d", city.getState().getId())));

        city.setState(state);
        return cityRepository.save(city);
    }

    public City update(City city) {
        City currentCity = find(city.getId());
        stateRepository.findById(city.getState().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um registro de Estado de código %d", city.getState().getId())));

        BeanUtils.copyProperties(city, currentCity);
        return cityRepository.save(currentCity);
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um registro de Cidade de código %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cidade de código %d não pode ser removido pois está em uso", id));
        }
    }
}
