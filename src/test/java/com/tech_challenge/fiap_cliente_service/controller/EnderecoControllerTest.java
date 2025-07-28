package com.tech_challenge.fiap_cliente_service.controller;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;
import com.tech_challenge.fiap_cliente_service.usecase.EnderecoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

    @Mock
    private EnderecoUseCase enderecoUseCase;

    @InjectMocks
    private EnderecoController enderecoController;

    private CreateEnderecoDTO createEnderecoDTO;
    private Endereco endereco;
    private String usuarioId;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID().toString();
        createEnderecoDTO = new CreateEnderecoDTO(
                "Rua Teste",
                "123",
                "12345-678",
                "Apto 101",
                usuarioId
        );
        endereco = Endereco.builder()
                .cep("12345-678")
                .rua("Rua Teste")
                .numero("123")
                .complemento("Apto 101")
                .build();
    }

    @Test
    void shouldCreateEnderecoAndReturnCreated() {
        when(enderecoUseCase.createEndereco(createEnderecoDTO)).thenReturn(endereco);

        ResponseEntity<Endereco> response = enderecoController.createEndereco(createEnderecoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(endereco, response.getBody());
        verify(enderecoUseCase, times(1)).createEndereco(createEnderecoDTO);
    }

    @Test
    void shouldReturnEnderecosByUsuarioIdAndReturnOk() {
        List<Endereco> expectedEnderecos = Collections.singletonList(endereco);
        when(enderecoUseCase.getByUsuarioId(usuarioId)).thenReturn(expectedEnderecos);

        ResponseEntity<List<Endereco>> response = enderecoController.getEnderecoByUsuarioId(usuarioId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEnderecos, response.getBody());
        verify(enderecoUseCase, times(1)).getByUsuarioId(usuarioId);
    }
}
