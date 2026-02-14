BEGIN TRANSACTION;
SELECT * FROM students;

INSERT INTO students(name)
VALUES('Маргарита Воронова');

UPDATE students SET name = 'Виталий Семенов'
WHERE id = 3;

SELECT * FROM students ORDER BY id;

COMMIT;

BEGIN TRANSACTION;

UPDATE students SET name = 'Маргарита Жукова'
WHERE id = 4;

SAVEPOINT first_savepoint;

INSERT INTO students(name)
VALUES('Тамара Светлова');

UPDATE students SET name = 'Маргарита Воронова'
WHERE id = 4;

SELECT * FROM students ORDER BY id;

ROLLBACK TO first_savepoint;

SELECT * FROM students ORDER BY id;

ROLLBACK;

SELECT * FROM students ORDER BY id;

