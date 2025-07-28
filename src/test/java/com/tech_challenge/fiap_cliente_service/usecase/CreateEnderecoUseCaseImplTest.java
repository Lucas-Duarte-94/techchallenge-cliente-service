package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.domain.entity.Usuario;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
import com.tech_challenge.fiap_cliente_service.gateway.EnderecoRepository;
import com.tech_challenge.fiap_cliente_service.gateway.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEnderecoUseCaseImplTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CreateEnderecoUseCaseImpl createEnderecoUseCase;

    private UUID usuarioId;
    private CreateEnderecoDTO createEnderecoDTO;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        createEnderecoDTO = new CreateEnderecoDTO(
                "12345-678",
                "Rua Teste",
                "123",
                "Apto 101",
                usuarioId.toString()
        );
        usuario = Usuario.builder()
                .id(usuarioId.toString())
                .nome("Teste")
                .build();
    }

    @Test
    void shouldCreateEnderecoSuccessfully() {
        when(usuarioRepository.findById(usuarioId.toString())).thenReturn(Optional.of(usuario));
        when(enderecoRepository.save(any(Endereco.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Endereco result = createEnderecoUseCase.execute(createEnderecoDTO);

        assertNotNull(result);
        assertEquals(createEnderecoDTO.cep(), result.getCep());
        assertEquals(createEnderecoDTO.rua(), result.getRua());
        assertEquals(createEnderecoDTO.numero(), result.getNumero());
        assertEquals(createEnderecoDTO.complemento(), result.getComplemento());
        assertEquals(usuario, result.getUsuario());

        verify(usuarioRepository, times(1)).findById(usuarioId.toString());
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void shouldThrowUsuarioNotFoundExceptionWhenUsuarioDoesNotExist() {
        when(usuarioRepository.findById(usuarioId.toString())).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> createEnderecoUseCase.execute(createEnderecoDTO));

        verify(usuarioRepository, times(1)).findById(usuarioId.toString());
        verify(enderecoRepository, never()).save(any(Endereco.class));
    }
}
