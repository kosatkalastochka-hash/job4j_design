CREATE DATABASE company_repository;
CREATE TABLE company(
id INT,
name TEXT,
date DATE,
website VARCHAR(128)
);
INSERT INTO company(id,name,date,website) VALUES (1,'Flowers','01-01-2003','flowers.com');
SELECT * FROM company;
UPDATE company SET id=2, name='Beautiful Flowers', website='beautifulFlowers.com';
SELECT * FROM company;
DELETE FROM company;
SELECT * FROM company;
