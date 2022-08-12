package com.evereats.fooder.jpa;

import com.evereats.fooder.domain.model.Kitchen;
import org.hibernate.boot.model.TypeDefinition;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class KitchenRegister {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Kitchen> list() {
        TypedQuery<Kitchen> query = entityManager.createQuery("SELECT c FROM Kitchen c", Kitchen.class);
        List<Kitchen> kitchens = query.getResultList();
        return kitchens;
    }
}
