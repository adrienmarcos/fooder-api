package com.evereats.fooder.infrastructure.repository;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Kitchen> list() {
        return entityManager.createQuery("SELECT k FROM Kitchen k", Kitchen.class).getResultList();
    }

    @Override
    public List<Kitchen> listByName(String name) {
        return entityManager.createQuery("SELECT k FROM Kitchen k WHERE k.name LIKE :name", Kitchen.class)
                .setParameter("name", "%" + name + "%").getResultList();
    }

    @Override
    public Kitchen findById(Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Kitchen kitchen = findById(id);

        if (kitchen == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(kitchen);
    }
}
