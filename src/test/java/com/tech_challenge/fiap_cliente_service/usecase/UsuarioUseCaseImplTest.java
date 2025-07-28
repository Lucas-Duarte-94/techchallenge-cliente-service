package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseImplTest {

    @Mock
    private ListAllUsersUseCase allUsersUseCase;

    @Mock
    private FindUserById findUserByIdUseCase;

    @InjectMocks
    private UsuarioUseCaseImpl usuarioUseCase;

    private String adminId;
    private String userId;
    private PublicUsuarioDTO publicUsuarioDTO;

    @BeforeEach
    void setUp() {
        adminId = UUID.randomUUID().toString();
        userId = UUID.randomUUID().toString();
        publicUsuarioDTO = new PublicUsuarioDTO(userId, "Test User");
    }

    @Test
    void shouldReturnAllUsers() {
        List<PublicUsuarioDTO> expectedUsers = Collections.singletonList(publicUsuarioDTO);
        when(allUsersUseCase.execute(adminId)).thenReturn(expectedUsers);

        List<PublicUsuarioDTO> result = usuarioUseCase.getUsuarios(adminId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedUsers, result);
        verify(allUsersUseCase, times(1)).execute(adminId);
    }

    @Test
    void shouldReturnUserById() {
        when(findUserByIdUseCase.execute(userId)).thenReturn(publicUsuarioDTO);

        PublicUsuarioDTO result = usuarioUseCase.getUsuarioById(userId);

        assertNotNull(result);
        assertEquals(publicUsuarioDTO, result);
        verify(findUserByIdUseCase, times(1)).execute(userId);
    }
}
