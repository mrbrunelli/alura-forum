CREATE TABLE answer(
    id BIGINT NOT NULL AUTO_INCREMENT,
    message VARCHAR(300) NOT NULL,
    created_at DATETIME NOT NULL,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    is_solution BOOLEAN NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(topic_id) REFERENCES topic(id),
    FOREIGN KEY(author_id) REFERENCES author(id)
);