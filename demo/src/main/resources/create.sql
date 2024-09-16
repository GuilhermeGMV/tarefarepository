DROP TABLE IF EXISTS livros;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE livros (
    id bigint,
    titulo VARCHAR(255),
    autor VARCHAR(255),
    ano int,
    userId bigint DEFAULT -1,
    PRIMARY KEY(id)
);

CREATE TABLE usuarios (
    id bigint,
    nome VARCHAR(255),
    data_nascimento DATE,
    PRIMARY KEY(id)
);
