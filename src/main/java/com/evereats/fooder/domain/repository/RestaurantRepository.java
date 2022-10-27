package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByFreightTaxBetween(BigDecimal initialFreightTax, BigDecimal finalFreightTax);
    Optional<Restaurant> findFirstByNameContaining(String name);
    List<Restaurant> findTop2ByNameContaining(String name);
    int countByKitchenId(Long kitchenId);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.kitchen")
    List<Restaurant> findAll();

    List<Restaurant> findByNameAndKitchen(@Param("name") String name, @Param("kitchenId") Long kitchenId);
}
