package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant findById(Long id);
    Restaurant save(Restaurant restaurant);
    void delete(Long id);
}
