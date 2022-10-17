package com.evereats.fooder.infrastructure.repository.spec;

import com.evereats.fooder.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withFreeFreight() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("freightTax"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSimilarName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
