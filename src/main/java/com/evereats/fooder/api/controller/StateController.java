package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.model.State;
import com.evereats.fooder.domain.repository.StateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private StateRepository stateRepository;

    public StateController(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @GetMapping
    public List<State> list() {
        return stateRepository.list();
    }
}
