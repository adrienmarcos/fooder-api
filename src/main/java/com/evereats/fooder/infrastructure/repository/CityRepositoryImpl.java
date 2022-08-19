package com.evereats.fooder.infrastructure.repository;

import com.evereats.fooder.domain.model.City;
import com.evereats.fooder.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> list() {
        return entityManager.createQuery("SELECT c FROM City c", City.class).getResultList();
    }

    @Override
    public City findById(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        City city = findById(id);

        if (city == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(city);
    }
}
