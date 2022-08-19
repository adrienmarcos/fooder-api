package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.State;
import com.evereats.fooder.domain.service.StateRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private StateRegisterService stateRegisterService;

    public StateController(StateRegisterService stateRegisterService) {
        this.stateRegisterService = stateRegisterService;
    }

    @GetMapping
    public ResponseEntity<List<State>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(stateRegisterService.list());
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> find(@PathVariable("stateId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stateRegisterService.find(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<State> save(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateRegisterService.save(state));
    }

    @PutMapping
    public ResponseEntity<State> update(@RequestBody State state) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stateRegisterService.update(state));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> delete(@PathVariable("stateId") Long id) {
        try {
            stateRegisterService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
