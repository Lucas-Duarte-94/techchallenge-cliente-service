package com.tech_challenge.fiap_cliente_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import com.tech_challenge.fiap_cliente_service.usecase.UsuarioUseCase;

@RestController
@RequestMapping("/usuario")
public class UserController {
    private UsuarioUseCase usuarioUseCase;

    public UserController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping("/list/{admin_id}")
    public ResponseEntity<List<PublicUsuarioDTO>> listUsers(@PathVariable("admin_id") String adminId) {
        var usuarios = this.usuarioUseCase.getUsuarios(adminId);
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicUsuarioDTO> getUsuarioById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.usuarioUseCase.getUsuarioById(id));
    }
}
