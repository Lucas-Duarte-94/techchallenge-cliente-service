package com.tech_challenge.fiap_cliente_service.controller;

import com.tech_challenge.fiap_cliente_service.exception.UsuarioDoesNotAuthorizedException;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionControllerTest {

    private GlobalExceptionController globalExceptionController;

    @BeforeEach
    void setUp() {
        globalExceptionController = new GlobalExceptionController();
    }

    @Test
    void shouldHandleUsuarioDoesNotAuthorizedException() {
        UsuarioDoesNotAuthorizedException exception = new UsuarioDoesNotAuthorizedException();
        ResponseEntity<String> response = globalExceptionController.handleUsuarioDoesNotAuthorizedException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void shouldHandleUsuarioNotFoundException() {
        UsuarioNotFoundException exception = new UsuarioNotFoundException();
        ResponseEntity<String> response = globalExceptionController.handleUsuarioNotFoundException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }
}