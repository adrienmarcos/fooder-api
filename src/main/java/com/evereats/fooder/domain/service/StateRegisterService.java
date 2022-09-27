package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.State;
import com.evereats.fooder.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateRegisterService {

    private StateRepository stateRepository;

    public StateRegisterService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> list() {
        return stateRepository.findAll();
    }

    public State find(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um registro de Estado de código %d", id)));

        return state;
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public State update(State state) {
        State currentState = find(state.getId());
        BeanUtils.copyProperties(state, currentState);
        return stateRepository.save(currentState);
    }

    public void delete(Long id) {
        try {
            stateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um registro de Estado de código %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de código %d não pode ser removido pois está em uso", id));
        }
    }
}
