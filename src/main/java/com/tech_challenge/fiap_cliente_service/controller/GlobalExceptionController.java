package com.tech_challenge.fiap_cliente_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tech_challenge.fiap_cliente_service.exception.UsuarioDoesNotAuthorizedException;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(UsuarioDoesNotAuthorizedException.class)
    public ResponseEntity<String> handleUsuarioDoesNotAuthorizedException(UsuarioDoesNotAuthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
