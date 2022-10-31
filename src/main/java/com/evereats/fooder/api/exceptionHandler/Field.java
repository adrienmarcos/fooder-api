package com.evereats.fooder.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Field {
    private String name;
    private String userMessage;
}
