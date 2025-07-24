package com.tech_challenge.fiap_cliente_service.usecase;

import java.util.List;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;

public interface UsuarioUseCase {
    List<PublicUsuarioDTO> getUsuarios(String adminId);

    PublicUsuarioDTO getUsuarioById(String id);
}
