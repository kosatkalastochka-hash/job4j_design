CREATE TABLE car_bodies(
id SERIAL PRIMARY KEY,
name VARCHAR(128)
);

CREATE TABLE car_engines(
id SERIAL PRIMARY KEY,
name VARCHAR(128)
);

CREATE TABLE car_transmissions(
id SERIAL PRIMARY KEY,
name VARCHAR(128)
);

CREATE TABLE cars (
id SERIAL PRIMARY KEY,
name VARCHAR(128),
body_id INT REFERENCES car_bodies(id),
engine_id INT REFERENCES car_engines(id),
transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES
('Седан'),
('Хэтчбек'),
('Внедорожник'),
('Купе'),
('Универсал');

SELECT * FROM car_bodies;

INSERT INTO car_engines (name) VALUES
('Бензиновый 1.6L'),
('Дизельный 2.0L'),
('Электрический'),
('Гибридный'),
('Бензиновый 2.5L');

SELECT * FROM car_engines;

INSERT INTO car_transmissions (name) VALUES
('Механическая'),
('Автоматическая'),
('Роботизированная'),
('Вариатор'),
('Полуавтоматическая');

SELECT * FROM car_transmissions;

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Camry', 1, 1, 2),
('Honda Civic', 2, 2, NULL),
('BMW X5', 3, NULL, 1),
('Audi A4', NULL, 3, 3),
('Volvo V90', 5, 4, 4);

SELECT * FROM cars;

SELECT c.*,b.name, e.name, t.name FROM cars c
LEFT JOIN car_bodies b ON b.id = c.body_id
LEFT JOIN car_engines e ON e.id = c.engine_id
LEFT JOIN car_transmissions t ON t.id = c.transmission_id;

SELECT b.* FROM cars c
RIGHT JOIN car_bodies b ON b.id = c.body_id
WHERE c.body_id  IS NULL;

SELECT e.* FROM cars c
RIGHT JOIN car_engines e ON e.id = c.engine_id
WHERE c.engine_id  IS NULL;

SELECT t.* FROM cars c
RIGHT JOIN car_transmissions t ON t.id = c.transmission_id
WHERE c.transmission_id  IS NULL;