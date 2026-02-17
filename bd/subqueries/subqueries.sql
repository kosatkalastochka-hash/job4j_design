CREATE TABLE customers
(
    id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Иван', 'Иванов', 25, 'Россия'),
('Мария', 'Петрова', 30, 'Россия'),
('Джон', 'Смит', 22, 'США'),
('Эмма', 'Уотсон', 28, 'Великобритания'),
('Ханс', 'Мюллер', 35, 'Германия'),
('Софи', 'Дюпон', 27, 'Франция'),
('Марко', 'Росси', 32, 'Италия'),
('Ларс', 'Андерсен', 40, 'Норвегия'),
('Анна', 'Новак', 29, 'Польша'),
('Карлос', 'Гарсия', 33, 'Испания');

SELECT * FROM customers  c
WHERE c.age = (SELECT MIN(c.age) FROM customers );

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES
(1500, 1),
(2300, 2),
(800, 3),
(3200, 4),
(1200, 5),
(2750, 6),
(1900, 1),
(950, 3),
(4100, 4),
(1650, 2);

SELECT * FROM customers c
WHERE c.id NOT IN(SELECT DISTINCT o.customer_id FROM  orders o);