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
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(80) NOT NULL,
    email    VARCHAR(50) UNIQUE
);

create table roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL REFERENCES users (id),
    role_id INT    NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users (id),
    price   INTEGER,
    status  VARCHAR(50)
);

DROP TABLE IF EXISTS orders_items;
CREATE TABLE orders_items
(
    id       BIGSERIAL PRIMARY KEY,
    order_id BIGINT        NOT NULL REFERENCES orders (id),
    book_id  BIGINT        NOT NULL REFERENCES books (id),
    count    BIGINT        NOT NULL,
    price    DECIMAL(8, 2) NOT NULL
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

INSERT INTO users (username, password, email)
VALUES ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);


COMMIT; 