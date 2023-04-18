CREATE TABLE author(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO author VALUES(1, 'Brunelli', 'brunelli@email.com');
INSERT INTO author VALUES(2, 'Jubileu', 'jubileu@email.com');