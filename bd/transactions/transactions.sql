CREATE TABLE users(
id SERIAL PRIMARY KEY,
first_name VARCHAR(128),
larst_name VARCHAR(128));

 INSERT INTO  users(first_name,larst_name)
VALUES
('Ludmila','Shlyapkina'),
('Ivan','Ivanov'),
('Olga','Verstova');

BEGIN ISOLATION LEVEL SERIALIZABLE;
BEGIN
UPDATE users SET larst_name = 'Petrova'
WHERE first_name = 'Olga';
UPDATE 1
COMMIT;
COMMIT

BEGIN ISOLATION LEVEL SERIALIZABLE;
BEGIN
UPDATE users SET larst_name = 'Sosedova'
WHERE first_name = 'Ludmila';
UPDATE 1
COMMIT;
ОШИБКА:  не удалось сериализовать доступ из-за зависимостей чтения/записи между транзакциями
ПОДРОБНОСТИ:  Reason code: Canceled on identification as a pivot, during commit attempt.
ПОДСКАЗКА:  Транзакция может завершиться успешно при следующей попытке.
postgres=#

