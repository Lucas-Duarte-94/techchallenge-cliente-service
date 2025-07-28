package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.domain.entity.Usuario;
import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
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
class FindUserByIdImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private FindUserByIdImpl findUserById;

    private UUID usuarioId;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        usuario = Usuario.builder()
                .id(usuarioId.toString())
                .nome("Teste")
                .build();
    }

    @Test
    void shouldReturnPublicUsuarioDTOWhenUserFound() {
        when(usuarioRepository.findById(usuarioId.toString())).thenReturn(Optional.of(usuario));

        PublicUsuarioDTO result = findUserById.execute(usuarioId.toString());

        assertNotNull(result);
        assertEquals(usuario.getId(), result.id());
        assertEquals(usuario.getNome(), result.nome());
        verify(usuarioRepository, times(1)).findById(usuarioId.toString());
    }

    @Test
    void shouldThrowUsuarioNotFoundExceptionWhenUserNotFound() {
        when(usuarioRepository.findById(usuarioId.toString())).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> findUserById.execute(usuarioId.toString()));

        verify(usuarioRepository, times(1)).findById(usuarioId.toString());
    }
}
