package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import com.evereats.fooder.domain.service.KitchenRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    private KitchenRepository kitchenRepository;
    private KitchenRegisterService kitchenRegisterService;

    public KitchenController(KitchenRepository kitchenRepository, KitchenRegisterService kitchenRegisterService) {
        this.kitchenRepository = kitchenRepository;
        this.kitchenRegisterService = kitchenRegisterService;
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(kitchenRegisterService.list());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> find(@PathVariable("kitchenId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(kitchenRegisterService.find(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Kitchen> save(@RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenRegisterService.save(kitchen));
    }

    @PutMapping
    public ResponseEntity<Kitchen> update(@RequestBody Kitchen kitchen) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(kitchenRegisterService.update(kitchen));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> delete(@PathVariable("kitchenId") Long id) {
        try {
            kitchenRegisterService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
