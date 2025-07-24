package com.tech_challenge.fiap_cliente_service.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super("Usuário não encontrado!");
    }
}
