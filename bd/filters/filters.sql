CREATE TABLE product(
id SERIAL PRIMARY KEY,
name VARCHAR(128),
type_id INT REFERENCES type(id),
expired_date DATE NOT NULL,
price FLOAT NOT NULL);

SELECT * FROM product;

CREATE TABLE type(
id SERIAL PRIMARY KEY,
name VARCHAR(128));

SELECT * FROM  type;

INSERT INTO type (name)
VALUES
('Сыр'),
('Молоко'),
('Мороженое'),
('Хлеб');

INSERT INTO product (name, type_id, expired_date, price)
VALUES
('Сыр "Российский"', 1, '2026-03-15', 450.00),
('Сыр "Гауда"', 1, '2026-04-10', 520.50),
('Сыр "Моцарелла"', 1, '2026-03-20', 380.00),
('Сыр "Чеддер"', 1, '2026-04-05', 490.00),
('Сыр "Сулугуни"', 1, '2026-03-25', 410.00),
('Молоко "Домик в деревне"', 2, '2026-02-18', 95.00),
('Молоко "Простоквашино"', 2, '2026-02-20', 89.90),
('Молоко "ВкусВилл"', 2, '2026-02-16', 102.50),
('Кефир "Активия"', 2, '2026-02-03', 75.00),
('Ряженка 3.2%', 2, '2026-02-17', 68.50),
('Сливки 20%', 2, '2026-02-19', 120.00),
('Мороженое "Пломбир"', 3, '2026-06-01', 150.00),
('Мороженое "Эскимо"', 3, '2026-07-15', 85.00),
('Мороженое ванильное', 3, '2026-05-20', 130.50),
('Мороженое шоколадное', 3, '2026-06-10', 135.00),
('Мороженое клубничное', 3, '2026-05-25', 140.00),
('Мороженое "Фисташка"', 3, '2026-07-01', 160.00),
('Мороженое "Крем-брюле"', 3, '2026-06-05', 145.00),
('Мороженое фруктовое', 3, '2026-04-30', 90.00),
('Мороженое с орехами', 3, '2026-02-01', 155.00),
('Мороженое "Три шоколада"', 3, '2026-07-10', 165.00),
('Мороженое "Сникерс"', 3, '2026-06-20', 170.00),
('Мороженое мятное', 3, '2026-05-15', 125.00),
('Мороженое "Радуга"', 3, '2026-06-30', 95.00),
('Мороженое кокосовое', 3, '2026-07-05', 148.00),
('Хлеб "Бородинский"', 4, '2026-02-10', 65.00),
('Батон нарезной', 4, '2026-02-09', 48.50),
('Хлеб "Цельнозерновой"', 4, '2026-02-12', 85.00),
('Лаваш армянский', 4, '2026-02-15', 120.00),
('Булочки сдобные', 4, '2026-02-04', 35.00),
('Хлеб "Ржаной"', 4, '2026-02-11', 70.00),
('Багет французский', 4, '2026-02-07', 95.00);

SELECT * FROM product p
JOIN  type t on p.type_id = t.id
AND t.name = 'Сыр';

SELECT * FROM product p
WHERE name ILIKE '%мороженое%';

SELECT * FROM product p
WHERE expired_date<NOW();

SELECT * FROM product
WHERE  price =(SELECT max(price) FROM product);

SELECT t.name,count(p.id) FROM type t
JOIN  product p on p.type_id = t.id
GROUP BY t.name;

SELECT * FROM product p
JOIN  type t on p.type_id = t.id
AND t.name IN('Сыр','Молоко');

SELECT t.name,count(p.id) FROM type t
JOIN  product p on p.type_id = t.id
GROUP BY t.name
HAVING count(p.id)<10 ;

SELECT p.*,t.name type_product
FROM product p
JOIN type t on p.type_id = t.id;
