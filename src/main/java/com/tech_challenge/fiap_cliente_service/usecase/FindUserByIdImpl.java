package com.tech_challenge.fiap_cliente_service.usecase;

import org.springframework.stereotype.Component;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
import com.tech_challenge.fiap_cliente_service.gateway.UsuarioRepository;

@Component
public class FindUserByIdImpl implements FindUserById {
    private final UsuarioRepository usuarioRepository;

    public FindUserByIdImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PublicUsuarioDTO execute(String id) {
        var usuario = this.usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);

        return new PublicUsuarioDTO(usuario.getId(), usuario.getNome());
    }

}
