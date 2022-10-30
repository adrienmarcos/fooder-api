package com.evereats.fooder.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    UNRECOGNIZED_PROPERTY("/unrecognized-property", "Unrecognized property"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
    IGNORED_PROPERTY("/ignored-property", "Ignored property"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    DOMAIN_ERROR("/domain-error", "Domain error");

    private String title;
    private String uri;

    ApiErrorType(String path, String title) {
        this.title = title;
        this.uri = "https://fooder.com.br".concat(path);
    }
}
