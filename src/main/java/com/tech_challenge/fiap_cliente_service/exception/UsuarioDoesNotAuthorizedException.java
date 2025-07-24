package com.tech_challenge.fiap_cliente_service.exception;

public class UsuarioDoesNotAuthorizedException extends RuntimeException {
    public UsuarioDoesNotAuthorizedException() {
        super("O usuário não possui permissão para essa funcionalidade!");
    }
}
