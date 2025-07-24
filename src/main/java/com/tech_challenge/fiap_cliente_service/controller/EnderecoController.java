package com.tech_challenge.fiap_cliente_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;
import com.tech_challenge.fiap_cliente_service.usecase.EnderecoUseCase;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoUseCase enderecoUseCase;

    public EnderecoController(EnderecoUseCase enderecoUseCase) {
        this.enderecoUseCase = enderecoUseCase;
    }

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody CreateEnderecoDTO enderecoDTO) {
        var endereco = this.enderecoUseCase.createEndereco(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @GetMapping("/{usuario_id}")
    public ResponseEntity<List<Endereco>> getEnderecoByUsuarioId(@PathVariable("usuario_id") String usuarioId) {
        var enderecos = this.enderecoUseCase.getByUsuarioId(usuarioId);
        return ResponseEntity.ok().body(enderecos);
    }
}
