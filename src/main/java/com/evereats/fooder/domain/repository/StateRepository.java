package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.State;

import java.util.List;

public interface StateRepository {
    List<State> list();
    State findById(Long id);
    State save(State state);
    void delete(Long id);
}
