package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal initialFreightTax, BigDecimal finalFreightTax);
    List<Restaurant> findWithFreeFreight(String name);
}
