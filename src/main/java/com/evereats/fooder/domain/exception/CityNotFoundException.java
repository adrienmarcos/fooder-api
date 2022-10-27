package com.evereats.fooder.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityID) {
        this(String.format("Não existe um registro de Cidade de código %d", cityID));
    }
}
