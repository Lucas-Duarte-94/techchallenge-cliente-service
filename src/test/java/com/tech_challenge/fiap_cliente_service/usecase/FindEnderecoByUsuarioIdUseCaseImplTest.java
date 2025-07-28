package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.domain.entity.Usuario;
import com.tech_challenge.fiap_cliente_service.gateway.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindEnderecoByUsuarioIdUseCaseImplTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private FindEnderecoByUsuarioIdUseCaseImpl findEnderecoByUsuarioIdUseCase;

    private String usuarioId;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID().toString();
        usuario = Usuario.builder()
                .id(usuarioId)
                .nome("Teste")
                .build();
    }

    @Test
    void shouldReturnEnderecosWhenFound() {
        Endereco endereco1 = Endereco.builder()
                .cep("11111-111")
                .rua("Rua A")
                .numero("1")
                .usuario(usuario)
                .build();
        Endereco endereco2 = Endereco.builder()
                .cep("22222-222")
                .rua("Rua B")
                .numero("2")
                .usuario(usuario)
                .build();
        List<Endereco> expectedEnderecos = Arrays.asList(endereco1, endereco2);

        when(enderecoRepository.findByUsuarioId(usuario.getId().toString())).thenReturn(expectedEnderecos);

        List<Endereco> result = findEnderecoByUsuarioIdUseCase.execute(usuarioId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedEnderecos, result);
        verify(enderecoRepository, times(1)).findByUsuarioId(usuarioId);
    }

    @Test
    void shouldReturnEmptyListWhenNoEnderecosFound() {
        when(enderecoRepository.findByUsuarioId(usuarioId)).thenReturn(Collections.emptyList());

        List<Endereco> result = findEnderecoByUsuarioIdUseCase.execute(usuarioId);

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(enderecoRepository, times(1)).findByUsuarioId(usuarioId);
    }
}
