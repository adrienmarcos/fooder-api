package com.evereats.fooder.api.controller;

import com.evereats.fooder.api.model.KitchensXmlWrapper;
import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml() {
        return new KitchensXmlWrapper(kitchenRepository.list());
    }

    @GetMapping("/{kitchenId}")
    public Kitchen find(@PathVariable("kitchenId") Long id) {
        return kitchenRepository.findById(id);
    }
}
