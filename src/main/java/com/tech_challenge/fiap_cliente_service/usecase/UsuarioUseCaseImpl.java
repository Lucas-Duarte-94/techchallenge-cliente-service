package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;

@Service
public class UsuarioUseCaseImpl implements UsuarioUseCase {
    private final ListAllUsersUseCase allUsersUseCase;
    private final FindUserById findUserByIdUseCase;

    public UsuarioUseCaseImpl(ListAllUsersUseCase allUsersUseCase, FindUserById findUserById) {
        this.allUsersUseCase = allUsersUseCase;
        this.findUserByIdUseCase = findUserById;
    }

    @Override
    public List<PublicUsuarioDTO> getUsuarios(String adminId) {
        return this.allUsersUseCase.execute(adminId);
    }

    @Override
    public PublicUsuarioDTO getUsuarioById(String id) {
        return this.findUserByIdUseCase.execute(id);
    }

}
