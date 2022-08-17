package com.evereats.fooder.api.controller;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    private KitchenRepository kitchenRepository;

    public KitchenController(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.list();
    }
}
