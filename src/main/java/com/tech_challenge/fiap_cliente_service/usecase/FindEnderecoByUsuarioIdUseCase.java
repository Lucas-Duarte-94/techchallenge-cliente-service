package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;

public interface FindEnderecoByUsuarioIdUseCase {
    List<Endereco> execute(String usuarioId);
}
