package com.evereats.fooder.domain.infrastructure.repository;

import com.evereats.fooder.domain.model.Restaurant;
import com.evereats.fooder.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialFreightTax, BigDecimal finalFreightTax) {
        var parameters = new HashMap<String, Object>();
        var jpql = new StringBuilder();
        jpql.append("SELECT r FROM Restaurant r WHERE 0 = 0 ");

        if (StringUtils.hasLength(name)) {
            jpql.append("AND name LIKE :name ");
            parameters.put("name", "%"+name+"%");
        }

        if (initialFreightTax != null) {
            jpql.append("AND freightTax >= :initialFreightTax ");
            parameters.put("initialFreightTax", initialFreightTax);
        }
        if (finalFreightTax != null) {
            jpql.append("AND freightTax <= :finalFreightTax ");
            parameters.put("finalFreightTax", finalFreightTax);
        }

        TypedQuery<Restaurant> query = entityManager.createQuery(jpql.toString(), Restaurant.class);
        parameters.forEach((key, value) -> query.setParameter(key, value));

        return query.getResultList();
    }
}
