package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.service.KitchenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping
    public List<Kitchen> list() {
        return kitchenService.list();
    }

    @GetMapping("/{kitchenId}")
    public Kitchen find(@PathVariable("kitchenId") Long id) {
        return kitchenService.find(id);
    }

    @GetMapping("/byName")
    public List<Kitchen> findByName(@RequestParam("name") String name) {
        return kitchenService.findByName(name);
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam("name") String name) {
        return kitchenService.exists(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody @Valid Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenID}")
    public Kitchen update(@PathVariable(name = "kitchenID") long kitchenID, @RequestBody @Valid Kitchen kitchen) {
        return kitchenService.update(kitchenID, kitchen);
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kitchenId") Long id) {
        kitchenService.delete(id);
    }
}