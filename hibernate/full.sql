BEGIN;

DROP TABLE IF EXISTS client CASCADE;
create TABLE client (id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) UNIQUE
);

INSERT INTO client (name) VALUES ('Ivan'),
                                    ('Sasha'),
                                    ('Lesha');

DROP TABLE IF EXISTS product CASCADE;

create TABLE product (id BIGSERIAL PRIMARY KEY,
                    name VARCHAR(255) UNIQUE,
                    current_price INTEGER
);

INSERT INTO product (name, current_price) VALUES ('bread', 20),
                                               ('milk', 60),
                                               ('eggs', 30),
                                               ('butter', 120);

DROP TABLE IF EXISTS purchases CASCADE;

create TABLE purchases(id BIGSERIAL PRIMARY KEY,
                       cost INTEGER,
                       client_id  INTEGER REFERENCES client (id) ON DELETE CASCADE,
                       product_id INTEGER REFERENCES product (id) ON DELETE CASCADE
);
INSERT INTO purchases (client_id, product_id, cost) VALUES (1, 1, 20),
                                                          (1, 2, 60),
                                                          (2, 3, 40),
                                                          (2, 4, 100),
                                                          (3, 1, 20),
                                                          (3, 3, 40);

COMMIT;