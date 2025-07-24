package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;

public interface FindUserById {
    PublicUsuarioDTO execute(String id);
}
