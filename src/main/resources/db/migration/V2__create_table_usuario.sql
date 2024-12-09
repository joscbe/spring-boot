CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(120) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO usuario VALUES (1, 'Josebe Barbosa', 'josebe.projex@gmail.com');