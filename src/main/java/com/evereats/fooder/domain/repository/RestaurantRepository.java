package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant findById(Long id);
    Restaurant save(Restaurant restaurant);
    void delete(Long id);
}
