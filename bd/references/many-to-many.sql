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
('Arni','Paramonov',NULL,3);

SELECT * FROM employee;

CREATE TABLE contacts(
id SERIAL PRIMARY KEY,
contact VARCHAR(128)
);

INSERT INTO contacts(contact)
VALUES
('30-60-70'),
('30-65-29'),
('30-63-24');

SELECT * FROM contacts;

CREATE TABLE employee_contacts(
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    contact_id INT REFERENCES contacts(id)
);

INSERT INTO employee_contacts(employee_id, contact_id)
VALUES
(1,2),
(2,2),
(2,1),
(3,3),
(4,1);

SELECT * FROM employee_contacts;