BEGIN;

DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    age  INTEGER
);
INSERT INTO clients (name, age)
VALUES ('Nicole', 23),
       ('Mario', 28),
       ('Fedor', 21),
       ('Igor', 33),
       ('Ivan', 19);

DROP TABLE IF EXISTS PRODUCTS CASCADE;
CREATE TABLE PRODUCTS
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    price INTEGER
);
INSERT INTO products (title, price)
VALUES ('phone', 45000),
       ('laptop', 60000),
       ('car', 1000000),
       ('book', 1000),
       ('ball', 1200);

COMMIT;