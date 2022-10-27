package com.evereats.fooder.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiError {
    private LocalDateTime timeStamp;
    private String message;
}
