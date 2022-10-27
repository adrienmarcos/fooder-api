package com.evereats.fooder.api.exceptionHandler;

import com.evereats.fooder.domain.exception.DomainException;
import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        var apiError = ApiError.builder().timeStamp(LocalDateTime.now()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException e) {
        var apiError = ApiError.builder().timeStamp(LocalDateTime.now()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException e) {
        var apiError = ApiError.builder().timeStamp(LocalDateTime.now()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        var apiError = ApiError.builder().timeStamp(LocalDateTime.now()).message("Media type nao suportado").build();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(apiError);
    }
}
