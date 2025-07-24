package com.tech_challenge.fiap_cliente_service.gateway;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {
    List<Endereco> findByUsuarioId(String usuarioId);
}
