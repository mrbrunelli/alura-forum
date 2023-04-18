CREATE TABLE course(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO course(id, name, category) VALUES (1, 'Kotlin', 'Programação');