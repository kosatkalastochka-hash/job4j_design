CREATE TABLE departments(
id SERIAL PRIMARY KEY,
name VARCHAR(128)
);

CREATE TABLE employees(
id SERIAL PRIMARY KEY,
name VARCHAR(128),
department_id INT REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES
('Отдел разработки'),
('Отдел маркетинга'),
('Финансовый отдел'),
('Отдел кадров');

INSERT INTO employees (name, department_id) VALUES
('Иванов Алексей', 1),
('Петрова Мария', 1),
('Сидоров Дмитрий', 1),
('Кузнецова Анна', NULL),
('Смирнов Владимир', 2),
('Попова Екатерина', 2),
('Васильев Игорь', 4),
('Николаева Ольга', 4),
('Алексеев Павел', 4),
('Федорова Юлия', NULL);

SELECT * FROM departments d
LEFT JOIN employees e ON e.department_id = d.id;

SELECT * FROM departments d
RIGHT JOIN employees e ON e.department_id = d.id;

SELECT * FROM departments d
FULL JOIN employees e ON e.department_id = d.id;

SELECT * FROM departments d
CROSS JOIN employees;

SELECT d.*,e.* FROM departments d
LEFT JOIN employees e ON e.department_id = d.id
WHERE e.id IS NULL;

SELECT * FROM departments d
LEFT JOIN employees e ON e.department_id = d.id;

SELECT d.*, e.*  FROM  employees e
RIGHT JOIN departments d ON e.department_id = d.id;


CREATE TABLE teens (
    name VARCHAR(100),
    gender VARCHAR(1)
);

INSERT INTO teens (name, gender) VALUES
('Алексей', 'M'),
('Мария', 'Ж'),
('Дмитрий', 'M'),
('Анна', 'Ж'),
('Иван', 'M'),
('Екатерина', 'Ж'),
('Сергей', 'M'),
('Ольга', 'Ж'),
('Андрей', 'M'),
('Наталья', 'Ж');

SELECT DISTINCT boy.name||' '||girl.name pair FROM
(SELECT * FROM teens
WHERE gender = 'M') boy
CROSS JOIN
(SELECT * FROM teens
WHERE gender = 'Ж') girl;
