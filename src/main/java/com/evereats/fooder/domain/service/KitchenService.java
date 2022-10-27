package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.exception.KitchenNotFoundException;
import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

    private static final String KITCHEN_NOT_FOUND = "Não existe um registro de Cozinha de código %d";
    private static final String KITCHEN_IN_USE = "Cozinha de código %d não pode ser removida, pois está em uso";
    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

    public Kitchen find(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(String.format(KITCHEN_NOT_FOUND, id)));
    }

    public boolean exists(String name) {
        return kitchenRepository.existsByName(name);
    }

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public Kitchen update(long kitchenID, Kitchen kitchen) {
        Kitchen currentKitchen = find(kitchenID);
        BeanUtils.copyProperties(kitchen, currentKitchen);
        return kitchenRepository.save(currentKitchen);
    }

    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(KITCHEN_NOT_FOUND, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(KITCHEN_IN_USE, id));
        }
    }

    public List<Kitchen> findByName(String name) {
        return kitchenRepository.findByNameContaining(name);
    }
}
