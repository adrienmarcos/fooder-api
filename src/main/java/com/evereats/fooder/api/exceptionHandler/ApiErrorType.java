package com.evereats.fooder.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    DOMAIN_ERROR("/domain-error", "Domain error");

    private String title;
    private String uri;

    ApiErrorType(String path, String title) {
        this.title = title;
        this.uri = "https://fooder.com.br".concat(path);
    }
}
