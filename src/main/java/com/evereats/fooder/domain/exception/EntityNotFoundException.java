package com.evereats.fooder.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntityNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
