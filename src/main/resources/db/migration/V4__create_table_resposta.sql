CREATE TABLE resposta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    topico_id BIGINT NOT NULL,
    message VARCHAR(120) NOT NULL,
    solucao INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY (topico_id) REFERENCES topico(id),
    FOREIGN KEY (created_by) REFERENCES usuario(id)
);