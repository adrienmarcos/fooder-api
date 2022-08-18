package com.evereats.fooder.domain.service;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class KitchenRegisterService {

    private KitchenRepository kitchenRepository;

    public KitchenRegisterService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    public Kitchen find(Long id) {
        Kitchen kitchen = kitchenRepository.findById(id);

        if (kitchen == null) {
            throw new EntityNotFoundException(String.format("Não existe um registro de cozinha de código %d", id));
        }

        return kitchen;
    }

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public Kitchen update(Kitchen kitchen) {
        Kitchen currentKitchen = find(kitchen.getId());
        BeanUtils.copyProperties(kitchen, currentKitchen);
        return kitchenRepository.save(currentKitchen);
    }

    public void delete(Long id) {
        try {
            kitchenRepository.delete(id);
        } catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um registro de cozinha de código %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
