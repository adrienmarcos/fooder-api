package com.evereats.fooder.infrastructure.repository;

import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.RestaurantRepository;
import com.evereats.fooder.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialFreightTax, BigDecimal finalFreightTax) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteriaQuery = criteriaBuilder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteriaQuery.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
        }

        if (initialFreightTax != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("freightTax"), initialFreightTax));
        }

        if (finalFreightTax != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("freightTax"), finalFreightTax));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Restaurant> typedQuery =  entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeFreight(String name) {
        return restaurantRepository.findWithFreeFreight(name);
    }
}
