package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;

public interface EnderecoUseCase {
    Endereco createEndereco(CreateEnderecoDTO enderecoDTO);

    List<Endereco> getByUsuarioId(String id);
}
