package com.tech_challenge.fiap_cliente_service.controller;

import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import com.tech_challenge.fiap_cliente_service.usecase.UsuarioUseCase;
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
class UserControllerTest {

    @Mock
    private UsuarioUseCase usuarioUseCase;

    @InjectMocks
    private UserController userController;

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
    void shouldListUsersAndReturnOk() {
        List<PublicUsuarioDTO> expectedUsers = Collections.singletonList(publicUsuarioDTO);
        when(usuarioUseCase.getUsuarios(adminId)).thenReturn(expectedUsers);

        ResponseEntity<List<PublicUsuarioDTO>> response = userController.listUsers(adminId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
        verify(usuarioUseCase, times(1)).getUsuarios(adminId);
    }

    @Test
    void shouldReturnUserByIdAndReturnOk() {
        when(usuarioUseCase.getUsuarioById(userId)).thenReturn(publicUsuarioDTO);

        ResponseEntity<PublicUsuarioDTO> response = userController.getUsuarioById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(publicUsuarioDTO, response.getBody());
        verify(usuarioUseCase, times(1)).getUsuarioById(userId);
    }
}
