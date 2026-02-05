CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE people
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES people (id)
);


INSERT INTO devices (name, price)
VALUES
('iPhone 15 Pro', 7000.99),
('Samsung Galaxy S24', 1200.50),
('MacBook Air M2', 8000.99),
('Dell XPS 13', 6000.00),
('Sony WH-1000XM5', 3490.99),
('iPad Air', 5990.00),
('Logitech MX Keys', 2990.99),
('Amazon Echo Dot', 1000.99);

INSERT INTO people (name)
VALUES
('Александр Петров'),
('Мария Иванова'),
('Дмитрий Смирнов'),
('Елена Кузнецова'),
('Артем Васильев'),
('Ольга Попова'),
('Игорь Новиков'),
('Анна Морозова'),
('Сергей Волков'),
('Наталья Романова');

INSERT INTO devices_people(device_id,people_id)
VALUES
(1,1),
(2,NULL),
(3,1),
(4,2),
(5,3),
(6,4),
(6,2),
(5,1),
(7,5),
(1,5),
(8,8),
(5,7),
(1,6),
(NULL,9),
(NULL,10);

SELECT avg(d.price) AS average_price
FROM devices AS d;

SELECT p.name, avg(d.price) AS average_price
FROM devices AS d
JOIN devices_people AS dp ON dp.device_id = d.id
JOIN people AS p ON dp.people_id  = p.id
GROUP BY p.id;

SELECT p.name, avg(d.price) AS average_price
FROM devices AS d
JOIN devices_people AS dp ON dp.device_id = d.id
JOIN people AS p ON dp.people_id  = p.id
GROUP BY p.id
HAVING avg(d.price)> 5000;