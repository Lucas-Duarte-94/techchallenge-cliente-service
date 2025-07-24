package com.tech_challenge.fiap_cliente_service.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech_challenge.fiap_cliente_service.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
