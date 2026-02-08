CREATE TABLE roles(
id SERIAL PRIMARY KEY,
name VARCHAR(128)
);

CREATE TABLE rules(
id SERIAL PRIMARY KEY,
right_access VARCHAR(260)
);

CREATE TABLE roles_rules(
id SERIAL PRIMARY KEY,
roles_id INT REFERENCES roles(id),
rules_id INT REFERENCES rules(id)
);

CREATE TABLE users(
id SERIAL PRIMARY KEY,
first_name VARCHAR(128),
larst_name VARCHAR(128),
roles_id INT REFERENCES roles(id)
);

CREATE TABLE states(
id SERIAL PRIMARY KEY,
status VARCHAR(128)
);

CREATE TABLE categories(
id SERIAL PRIMARY KEY,
category VARCHAR(128)
);

CREATE TABLE items(
id SERIAL PRIMARY KEY,
name VARCHAR(128),
user_id INT REFERENCES users(id),
categories_id INT REFERENCES categories(id),
states_id INT REFERENCES states(id)
);


CREATE TABLE comments(
id SERIAL PRIMARY KEY,
comment VARCHAR(128),
items_id INT REFERENCES items(id)
);

CREATE TABLE attachs(
id SERIAL PRIMARY KEY,
file_path VARCHAR(260),
items_id INT REFERENCES items(id)
);
