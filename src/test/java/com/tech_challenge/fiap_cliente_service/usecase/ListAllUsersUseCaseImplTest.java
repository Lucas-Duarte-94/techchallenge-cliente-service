package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.domain.entity.Usuario;
import com.tech_challenge.fiap_cliente_service.dto.PublicUsuarioDTO;
import com.tech_challenge.fiap_cliente_service.dto.RoleEnum;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioDoesNotAuthorizedException;
import com.tech_challenge.fiap_cliente_service.exception.UsuarioNotFoundException;
import com.tech_challenge.fiap_cliente_service.gateway.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListAllUsersUseCaseImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ListAllUsersUseCaseImpl listAllUsersUseCase;

    private String adminId;
    private String nonAdminId;
    private Usuario adminUser;
    private Usuario regularUser;
    private Usuario user1;
    private Usuario user2;

    @BeforeEach
    void setUp() {
        adminId = UUID.randomUUID().toString();
        nonAdminId = UUID.randomUUID().toString();

        adminUser = Usuario.builder()
                .id(adminId)
                .nome("Admin User")
                .funcao(RoleEnum.ADMIN)
                .build();

        regularUser = Usuario.builder()
                .id(nonAdminId)
                .nome("Regular User")
                .funcao(RoleEnum.CLIENTE)
                .build();

        user1 = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .nome("User One")
                .funcao(RoleEnum.CLIENTE)
                .build();

        user2 = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .nome("User Two")
                .funcao(RoleEnum.CLIENTE)
                .build();
    }

    @Test
    void shouldListAllUsersWhenAdminIsAuthorized() {
        when(usuarioRepository.findById(adminId)).thenReturn(Optional.of(adminUser));
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(adminUser, regularUser, user1, user2));

        List<PublicUsuarioDTO> result = listAllUsersUseCase.execute(adminId);

        assertNotNull(result);
        assertEquals(4, result.size());
        assertTrue(result.stream().anyMatch(dto -> dto.id().equals(adminUser.getId())));
        assertTrue(result.stream().anyMatch(dto -> dto.id().equals(regularUser.getId())));
        assertTrue(result.stream().anyMatch(dto -> dto.id().equals(user1.getId())));
        assertTrue(result.stream().anyMatch(dto -> dto.id().equals(user2.getId())));

        verify(usuarioRepository, times(1)).findById(adminId);
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void shouldThrowUsuarioNotFoundExceptionWhenAdminDoesNotExist() {
        when(usuarioRepository.findById(adminId)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> listAllUsersUseCase.execute(adminId));

        verify(usuarioRepository, times(1)).findById(adminId);
        verify(usuarioRepository, never()).findAll();
    }

    @Test
    void shouldThrowUsuarioDoesNotAuthorizedExceptionWhenUserIsNotAdmin() {
        when(usuarioRepository.findById(nonAdminId)).thenReturn(Optional.of(regularUser));

        assertThrows(UsuarioDoesNotAuthorizedException.class, () -> listAllUsersUseCase.execute(nonAdminId));

        verify(usuarioRepository, times(1)).findById(nonAdminId);
        verify(usuarioRepository, never()).findAll();
    }
}
