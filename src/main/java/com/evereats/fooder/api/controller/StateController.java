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
    public ResponseEntity<List<State>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.list());
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> find(@PathVariable("stateId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stateService.find(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<State> save(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(state));
    }

    @PutMapping
    public ResponseEntity<State> update(@RequestBody State state) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stateService.update(state));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("stateId") Long id) {
        stateService.delete(id);
    }
}
