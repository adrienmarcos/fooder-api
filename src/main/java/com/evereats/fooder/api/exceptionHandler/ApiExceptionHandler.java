package com.evereats.fooder.api.exceptionHandler;

import com.evereats.fooder.domain.exception.DomainException;
import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        var apiError = createApiErrorBuilder(HttpStatus.NOT_FOUND, ApiErrorType.ENTITY_NOT_FOUND, ex.getMessage()).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex, WebRequest request) {
        var apiError = createApiErrorBuilder(HttpStatus.BAD_REQUEST, ApiErrorType.DOMAIN_ERROR, ex.getMessage()).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        var apiError = createApiErrorBuilder(HttpStatus.CONFLICT, ApiErrorType.ENTITY_IN_USE, ex.getMessage()).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (ExceptionUtils.getRootCause(ex) instanceof InvalidFormatException) {
            return handleInvalidFormatException(
                    (InvalidFormatException) ExceptionUtils.getRootCause(ex), headers, status, request);
        }

        if (ExceptionUtils.getRootCause(ex) instanceof IgnoredPropertyException) {
            return handleIgnoredPropertyException(
                    (IgnoredPropertyException) ExceptionUtils.getRootCause(ex), headers, status, request);
        }

        if (ExceptionUtils.getRootCause(ex) instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedPropertyException(
                    (UnrecognizedPropertyException) ExceptionUtils.getRootCause(ex), headers, status, request);
        }

        var apiError = createApiErrorBuilder(HttpStatus.BAD_REQUEST, ApiErrorType.MESSAGE_NOT_READABLE,
                "The request's body it's not valid. Check syntax error.").build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleIgnoredPropertyException(
            IgnoredPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var path = ex.getPath().stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        var apiError = createApiErrorBuilder(status, ApiErrorType.IGNORED_PROPERTY,
                String.format("The property '%s' is ignored by the api. Remove it and try again", path)).build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(
            UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var path = ex.getPath().stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        var apiError = createApiErrorBuilder(status, ApiErrorType.UNRECOGNIZED_PROPERTY,
                String.format("The property '%s' is unrecognized by the api. Remove it and try again", path)).build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ApiError.builder().title(status.getReasonPhrase()).status(status.value()).build();
        } else if (body instanceof String) {
            body = ApiError.builder().title((String) body).status(status.value()).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType apiErrorType, String detail) {
        return ApiError.builder()
                .status(status.value())
                .type(apiErrorType.getUri())
                .title(apiErrorType.getTitle())
                .detail(detail);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var path = ex.getPath().stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        var apiError = createApiErrorBuilder(status, ApiErrorType.MESSAGE_NOT_READABLE,
                String.format("The property '%s' received the value '%s' who is a invalid type. Fix it and enter a " +
                        "value compatible with the type %s", path, ex.getValue(), ex.getTargetType().getSimpleName())).build();
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }
}