CREATE TABLE employee(
id SERIAL PRIMARY KEY,
first_name VARCHAR(128) NOT NULL,
last_name VARCHAR(128) NOT NULL,
salary INT,
work_books_id INT REFERENCES work_books(id) UNIQUE,
company_id INT REFERENCES  company(id),
UNIQUE (first_name,last_name)
);

INSERT INTO employee(first_name,last_name,salary,work_books_id,company_id)
VALUES('Ivan','Ivanov',1000,1,1),
('Petr','Petrov',2000,2,2),
('Sveta','Sveticova',1500,3,NULL),
('Arni','Paramonov',NULL,4,3);

SELECT * FROM employee;

CREATE TABLE work_books(
id SERIAL PRIMARY KEY,
seria INT,
number INT);

INSERT INTO work_books(seria, number)
VALUES
(12,57647),
(34,38465),
(45,98765),
(99,46537);

SELECT * FROM  work_books;