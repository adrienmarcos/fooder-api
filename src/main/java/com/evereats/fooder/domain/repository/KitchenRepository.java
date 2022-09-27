package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    //List<Kitchen> listByName(String name);
}
