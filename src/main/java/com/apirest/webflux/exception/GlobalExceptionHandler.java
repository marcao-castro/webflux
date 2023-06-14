package com.apirest.webflux.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<?>> errorHandler(Exception error) {
        return Mono.just(ResponseEntity.internalServerError().build());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> errorNotFoundException(PersonNotFoundException error) {
        var body = ErrorResponse.builder(error, HttpStatus.NOT_FOUND, "person not found").build();
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(body));

    }
}
