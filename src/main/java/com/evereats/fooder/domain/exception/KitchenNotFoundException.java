package com.evereats.fooder.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class KitchenNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long kitchenID) {
        this(String.format("Não existe um registro de Cozinha de código %d", kitchenID));
    }
}
