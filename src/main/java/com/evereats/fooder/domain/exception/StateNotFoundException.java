package com.evereats.fooder.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateID) {
        this(String.format("Não existe um cadastro de estado com código %d", stateID));
    }
}
