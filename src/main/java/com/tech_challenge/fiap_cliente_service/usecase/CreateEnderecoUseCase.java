package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;

public interface CreateEnderecoUseCase {
    Endereco execute(CreateEnderecoDTO enderecoDTO);
}
