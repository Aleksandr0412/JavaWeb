BEGIN;

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255),
    description  VARCHAR(5000),
    genre        VARCHAR(255),
    price        NUMERIC(8, 2),
    publish_year INTEGER
);
INSERT INTO books (title, description, genre, price, publish_year)
VALUES ('Harry Potter 1', 'Description 1', 'FANTASY', 300.0, 2000),
       ('Harry Potter 2', 'Description 2', 'FANTASY', 400.0, 2001),
       ('Harry Potter 3', 'Description 3', 'FANTASY', 500.0, 2002),
       ('Harry Potter 4', 'Description 4', 'FANTASY', 700.0, 2003),
       ('Harry Potter 5', 'Description 5', 'FANTASY', 440.0, 2004),
       ('Harry Potter 6', 'Description 6', 'FANTASY', 650.0, 2005),
       ('Harry Potter 7', 'Description 7', 'FANTASY', 200.0, 2006),
       ('LOTR 1', 'Description 8', 'FANTASTIC', 1200.0, 2006),
       ('LOTR 2', 'Description 9', 'FANTASTIC', 900.0, 2004),
       ('LOTR 3', 'Description 10', 'FANTASTIC', 600.0, 2001),
       ('Hobbit', 'Description 11', 'FANTASTIC', 500.0, 2001),
       ('Sherlock Holmes 1', 'Description 12', 'DETECTIVE', 400.0, 1800),
       ('Sherlock Holmes 2', 'Description 13', 'DETECTIVE', 550.0, 1803),
       ('Sherlock Holmes 3', 'Description 14', 'DETECTIVE', 650.0, 1900);


COMMIT; 