CREATE TABLE roles(
id SERIAL PRIMARY KEY,
name VARCHAR(128)
);

SELECT * FROM roles;

CREATE TABLE rules(
id SERIAL PRIMARY KEY,
right_access VARCHAR(260)
);

SELECT * FROM rules;

CREATE TABLE roles_rules(
id SERIAL PRIMARY KEY,
roles_id INT REFERENCES roles(id),
rules_id INT REFERENCES rules(id)
);

SELECT * FROM roles_rules;

CREATE TABLE comments(
id SERIAL PRIMARY KEY,
comment VARCHAR(128)
);

SELECT * FROM comments;

CREATE TABLE attachs(
id SERIAL PRIMARY KEY,
file_path VARCHAR(260)
);

SELECT * FROM attachs;

CREATE TABLE items(
id SERIAL PRIMARY KEY,
name VARCHAR(128),
comments_id INT REFERENCES comments(id),
attachs_id INT REFERENCES attachs(id)
);

SELECT * FROM items;

CREATE TABLE users(
id SERIAL PRIMARY KEY,
first_name VARCHAR(128),
larst_name VARCHAR(128),
roles_id INT REFERENCES roles(id),
items_id INT REFERENCES items(id)
);

SELECT * FROM users;

CREATE TABLE states(
id SERIAL PRIMARY KEY,
status VARCHAR(128),
items_id INT REFERENCES items(id)
);

SELECT * FROM states;

CREATE TABLE categories(
id SERIAL PRIMARY KEY,
category VARCHAR(128),
items_id INT REFERENCES items(id)
);

SELECT * FROM categories;
