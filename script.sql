
CREATE DATABASE IF NOT EXISTS csv_manager
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE csv_manager;
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL UNIQUE
);

INSERT INTO role (nome) VALUES
('ADMIN'),
('VISUALIZADOR');

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_role
        FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE INDEX idx_usuario_email ON usuario(email);

CREATE TABLE dataset (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    tema VARCHAR(100) NOT NULL,
    orgao VARCHAR(150) NOT NULL,
    contato_responsavel VARCHAR(150) NOT NULL,
    descricao TEXT,
    ano_inicio INT NOT NULL,
    ano_fim INT NOT NULL,
    drive_url TEXT NOT NULL,
    status ENUM('PENDENTE', 'APROVADO', 'REPROVADO', 'OBSERVACAO')
           DEFAULT 'PENDENTE',
    criado_por BIGINT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_dataset_usuario
        FOREIGN KEY (criado_por) REFERENCES usuario(id)
);

CREATE INDEX idx_dataset_status ON dataset(status);
CREATE INDEX idx_dataset_ano ON dataset(ano_inicio, ano_fim);