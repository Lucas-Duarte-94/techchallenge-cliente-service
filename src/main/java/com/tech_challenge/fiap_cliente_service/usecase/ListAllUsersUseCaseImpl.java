package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import com.tech_challenge.fiap_cliente_service.dto.RoleEnum;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioDoesNotAuthorizedException;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
import com.tech_challenge.fiap_cliente_service.gateway.UsuarioRepository;

@Component
public class ListAllUsersUseCaseImpl implements ListAllUsersUseCase {
    private final UsuarioRepository usuarioRepository;

    public ListAllUsersUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<PublicUsuarioDTO> execute(String adminId) {
        var user = this.usuarioRepository.findById(adminId).orElseThrow(UsuarioNotFoundException::new);

        if (user.getFuncao() != RoleEnum.ADMIN) {
            throw new UsuarioDoesNotAuthorizedException();
        }

        var usuarios = this.usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new PublicUsuarioDTO(usuario.getId(), usuario.getNome())).toList();
    }

}
