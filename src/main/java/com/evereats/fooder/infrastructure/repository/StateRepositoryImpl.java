package com.evereats.fooder.infrastructure.repository;

import com.evereats.fooder.domain.model.State;
import com.evereats.fooder.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<State> list() {
        return entityManager.createQuery("SELECT s FROM State s", State.class).getResultList();
    }

    @Override
    public State findById(Long id) {
        return entityManager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return entityManager.merge(state);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        State state = findById(id);

        if (state == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(state);
    }
}
