package com.evereats.fooder.infrastructure.repository;

import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.RestaurantRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Restaurant> list() {
        return entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Restaurant restaurant = findById(id);

        if (restaurant == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(restaurant);
    }
}
