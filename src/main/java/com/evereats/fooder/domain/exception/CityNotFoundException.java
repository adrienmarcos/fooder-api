package com.evereats.fooder.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityID) {
        this(String.format("Não existe um registro de Cidade de código %d", cityID));
    }
}
