package com.tech_challenge.fiap_cliente_service.dto;

public record CreateEnderecoDTO(
        String rua,
        String numero,
        String cep,
        String complemento,
        String usuarioId) {

}
