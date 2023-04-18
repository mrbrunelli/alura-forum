CREATE TABLE course(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO course(id, name, category) VALUES (1, 'Kotlin', 'Programação');
INSERT INTO course(id, name, category) VALUES (2, 'React', 'Front-end');
INSERT INTO course(id, name, category) VALUES (3, 'Figma', 'UX/UI');