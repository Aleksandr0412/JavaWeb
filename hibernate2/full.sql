BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO users (name)
VALUES ('Alex'),
       ('Alexandr'),
       ('Aleksey'),
       ('Dima'),
       ('Sergey'),
       ('Vitya'),
       ('Misha'),
       ('Evgeniy');

DROP TABLE IF EXISTS lots CASCADE;
CREATE TABLE lots
(
    id             BIGSERIAL PRIMARY KEY,
    title          VARCHAR(255),
    current_bet    INTEGER,
    last_bet_owner INTEGER REFERENCES users (id),
    version        INTEGER
);
INSERT INTO lots (title, current_bet, version)
VALUES ('ring', 0, 0),
       ('braslet', 0, 0),
       ('monument', 0, 0),
       ('book', 0, 0);


COMMIT;