package com.evereats.fooder.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restaurantID) {
        this(String.format("Não existe um registro de Restaurante de código %d", restaurantID));
    }
}
