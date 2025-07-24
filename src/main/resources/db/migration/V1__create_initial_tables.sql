
CREATE TABLE usuario (
    id VARCHAR(36) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    funcao VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE endereco (
    id VARCHAR(36) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    usuario_id VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
