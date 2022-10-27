package com.evereats.fooder.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found");

    private String title;
    private String uri;

    ApiErrorType(String title, String path) {
        this.title = title;
        this.uri = "https://fooder.com.br".concat(path);
    }
}
