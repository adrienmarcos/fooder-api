package com.evereats.fooder.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restaurantID) {
        this(String.format("Não existe um registro de Restaurante de código %d", restaurantID));
    }
}
