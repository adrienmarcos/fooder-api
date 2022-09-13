package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Kitchen;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KitchenRepository {
    List<Kitchen> list();
    List<Kitchen> listByName(String name);
    Kitchen findById(Long id);
    Kitchen save(Kitchen kitchen);
    void delete(Long id);
}
