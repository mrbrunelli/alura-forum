INSERT INTO author(name, email, password) VALUES ('admin', 'admin@email.com', '$2a$12$yNv6TzkKkhRETPUJCEvXc.c7hy.Q.evk30bbF0OOwQ85wSeNR5Jdm');
INSERT INTO role(name) VALUES ('ADMIN');
INSERT INTO author_role(author_id, role_id) VALUES(3, 2);