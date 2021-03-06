CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table products(
id uuid DEFAULT uuid_generate_v4 (),
name varchar(80),
price int
);

INSERT INTO products (name, price) VALUES ('Книга', 120);
INSERT INTO products (name, price) VALUES ('Носок', 10);
INSERT INTO products (name, price) VALUES ('Презерватив', 77);


create table shipping(
id uuid DEFAULT uuid_generate_v4 (),
product_id uuid,
items int
);

create table sale(
id uuid DEFAULT uuid_generate_v4 (),
product_id uuid,
items int
);

