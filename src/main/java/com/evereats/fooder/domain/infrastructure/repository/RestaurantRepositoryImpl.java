package com.evereats.fooder.domain.infrastructure.repository;

import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialFreightTax, BigDecimal finalFreightTax) {
        var jpql = "SELECT r FROM Restaurant r " +
                   "WHERE r.name LIKE :name AND r.freightTax BETWEEN :initialFreightTax AND :finalFreightTax";

        return entityManager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%"+name+"%")
                .setParameter("initialFreightTax", initialFreightTax)
                .setParameter("finalFreightTax", finalFreightTax)
                .getResultList();
    }
}
