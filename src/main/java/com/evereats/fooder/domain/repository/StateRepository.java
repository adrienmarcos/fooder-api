package com.evereats.fooder.domain.repository;

import com.evereats.fooder.domain.model.State;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StateRepository {
    List<State> list();
    State findById(Long id);
    State save(State state);
    void delete(Long id);
}
