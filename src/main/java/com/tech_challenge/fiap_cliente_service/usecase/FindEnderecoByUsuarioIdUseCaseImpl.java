package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.gateway.EnderecoRepository;

@Component
public class FindEnderecoByUsuarioIdUseCaseImpl implements FindEnderecoByUsuarioIdUseCase {
    private final EnderecoRepository enderecoRepository;

    public FindEnderecoByUsuarioIdUseCaseImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<Endereco> execute(String usuarioId) {
        return this.enderecoRepository.findByUsuarioId(usuarioId);

    }

}
