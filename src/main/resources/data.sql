
INSERT INTO library (name, balance)
    VALUES ('Stefanyk Library', 10000000)
    ON CONFLICT (name) DO NOTHING;

INSERT INTO category (name)
    VALUES ('USER'), ('ADMIN'), ('ELDERLY'), ('STUDENT')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO genre (name)
    VALUES ('Action'), ('Adventure'), ('Comedy'), ('Detective'), ('Drama'), ('Novel'), ('History')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO users (name, address, phone_number, email, password, balance, category_id, library_id)
    VALUES ('root', 'Rynok sq. 1', '+3800000000', 'root@gmail.com', 'Y6nw6nu5gFB5a2SehUgYRQ==',
        100000, (select id from category where name = 'ADMIN'), 1)
    ON CONFLICT (email) DO NOTHING;
