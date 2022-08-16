package com.evereats.fooder.infrastructure.repository;

import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public Restaurant add(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Override
    public void remove(Restaurant restaurant) {
        restaurant = findById(restaurant.getId());
        entityManager.remove(restaurant);
    }
}
