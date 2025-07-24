package com.tech_challenge.fiap_cliente_service.domain.entity;

import java.util.Date;
import java.util.List;

import com.tech_challenge.fiap_cliente_service.dto.RoleEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String CPF;

    @Column(nullable = false)
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private RoleEnum funcao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Endereco> endereco;
}
