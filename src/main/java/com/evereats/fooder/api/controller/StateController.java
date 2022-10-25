package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.State;
import com.evereats.fooder.domain.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public List<State> list() {
        return stateService.list();
    }

    @GetMapping("/{stateId}")
    public State find(@PathVariable("stateId") Long id) {
        return stateService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping
    public State update(@RequestBody State state) {
        return stateService.update(state);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("stateId") Long id) {
        stateService.delete(id);
    }
}
