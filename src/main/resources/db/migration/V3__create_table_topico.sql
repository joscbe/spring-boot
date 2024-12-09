CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    curso_id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,
    message VARCHAR(120) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (created_by) REFERENCES usuario(id)
);