package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;

@Service
public class EnderecoUseCaseImpl implements EnderecoUseCase {
    private FindEnderecoByUsuarioIdUseCase findEnderecoByUsuarioIdUseCase;
    private CreateEnderecoUseCase createEnderecoUseCase;

    public EnderecoUseCaseImpl(FindEnderecoByUsuarioIdUseCase findEnderecoByUsuarioIdUseCase,
            CreateEnderecoUseCase createEnderecoUseCase) {
        this.createEnderecoUseCase = createEnderecoUseCase;
        this.findEnderecoByUsuarioIdUseCase = findEnderecoByUsuarioIdUseCase;
    }

    @Override
    public Endereco createEndereco(CreateEnderecoDTO enderecoDTO) {
        return this.createEnderecoUseCase.execute(enderecoDTO);
    }

    @Override
    public List<Endereco> getByUsuarioId(String id) {
        return this.findEnderecoByUsuarioIdUseCase.execute(id);
    }

}
