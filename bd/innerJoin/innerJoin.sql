CREATE TABLE employee(
id SERIAL PRIMARY KEY,
first_name VARCHAR(128) NOT NULL,
last_name VARCHAR(128) NOT NULL,
salary INT,
company_id INT REFERENCES  company(id),
UNIQUE (first_name,last_name)
);

INSERT INTO employee(first_name,last_name,salary,company_id)
VALUES('Ivan','Ivanov',1000,1),
('Petr','Petrov',2000,2),
('Sveta','Sveticova',1500,NULL),
('Arni','Paramonov',NULL,3),
('Oleg','Eremin',2000,2);

SELECT * FROM employee;

CREATE TABLE company(
id INT PRIMARY KEY,
name VARCHAR(128) UNIQUE NOT NULL,
date DATE NOT NULL CHECK(date>'1995-01-01' AND date < '2020-01-01')
);

INSERT INTO company(id,name,date)
VALUES
(1,'Googl','2001-01-01'),
(2,'Appele','2000-10-01'),
(3,'Facebook','1998-02-10');

SELECT * FROM company;

SELECT c.name,e.first_name||' '||e.last_name AS fio
FROM company AS c
JOIN employee AS e
ON c.id = e.company_id;

SELECT c.name,e.salary
FROM company AS c
JOIN employee AS e
ON c.id = e.company_id;

SELECT c.name,count(e.id)
FROM company AS c
JOIN employee AS e
ON c.id = e.company_id
GROUP BY c.name;