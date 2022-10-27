package com.evereats.fooder.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StateNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateID) {
        this(String.format("Não existe um cadastro de estado com código %d", stateID));
    }
}
