package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {
    List<Kitchen> list();
    Kitchen findById(Long id);
    Kitchen save(Kitchen kitchen);
    void delete(Long id);
}
