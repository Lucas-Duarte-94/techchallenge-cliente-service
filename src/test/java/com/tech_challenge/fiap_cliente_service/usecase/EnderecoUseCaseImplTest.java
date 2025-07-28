package com.tech_challenge.fiap_cliente_service.usecase;

import com.tech_challenge.fiap_cliente_service.domain.entity.Endereco;
import com.tech_challenge.fiap_cliente_service.dto.CreateEnderecoDTO;
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
class EnderecoUseCaseImplTest {

    @Mock
    private FindEnderecoByUsuarioIdUseCase findEnderecoByUsuarioIdUseCase;

    @Mock
    private CreateEnderecoUseCase createEnderecoUseCase;

    @InjectMocks
    private EnderecoUseCaseImpl enderecoUseCase;

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
    void shouldCreateEnderecoSuccessfully() {
        when(createEnderecoUseCase.execute(createEnderecoDTO)).thenReturn(endereco);

        Endereco result = enderecoUseCase.createEndereco(createEnderecoDTO);

        assertNotNull(result);
        assertEquals(endereco, result);
        verify(createEnderecoUseCase, times(1)).execute(createEnderecoDTO);
    }

    @Test
    void shouldReturnEnderecosByUsuarioId() {
        List<Endereco> expectedEnderecos = Collections.singletonList(endereco);
        when(findEnderecoByUsuarioIdUseCase.execute(usuarioId)).thenReturn(expectedEnderecos);

        List<Endereco> result = enderecoUseCase.getByUsuarioId(usuarioId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedEnderecos, result);
        verify(findEnderecoByUsuarioIdUseCase, times(1)).execute(usuarioId);
    }
}
