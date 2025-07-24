package com.tech_challenge.fiap_cliente_service.usecase;

import org.springframework.stereotype.Component;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
import com.tech_challenge.fiap_cliente_service.gateway.EnderecoRepository;
import com.tech_challenge.fiap_cliente_service.gateway.UsuarioRepository;

@Component
public class CreateEnderecoUseCaseImpl implements CreateEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public CreateEnderecoUseCaseImpl(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Endereco execute(CreateEnderecoDTO enderecoDTO) {
        var usuario = this.usuarioRepository.findById(enderecoDTO.usuarioId())
                .orElseThrow(UsuarioNotFoundException::new);

        var novoEndereco = Endereco.builder()
                .cep(enderecoDTO.cep())
                .complemento(enderecoDTO.complemento())
                .rua(enderecoDTO.rua())
                .numero(enderecoDTO.numero())
                .usuario(usuario)
                .build();

        return this.enderecoRepository.save(novoEndereco);
    }

}
