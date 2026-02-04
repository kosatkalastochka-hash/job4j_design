CREATE TABLE fauna
(id             SERIAL PRIMARY KEY,
 name           TEXT,
 avg_age        INT,
 discovery_date DATE
);

SELECT * FROM  fauna;

INSERT INTO fauna(name,avg_age,discovery_date)
VALUES
('African elephant', 21900, '1797-01-01'),
('Bowhead whale', 36500, '1823-01-01'),
('Greenland shark', 219000, '1801-01-01'),
('Madagascar hissing cockroach', 730, '1853-01-01'),
('Lionfish ', 3650, '1758-01-01'),
('Starfish ', 1825, '1758-01-01'),
('Ocean quahog ', 255500, '1767-01-01'),
('Japanese giant salamander', 18250, '1820-01-01'),
('Gray wolf', 6570, '1758-01-01'),
('Brown bear', 9125, '1758-01-01'),
('Coelacanth ', 18250, NULL),
('Tardigrade ', 365, NULL);

SELECT * FROM fauna;

SELECT * FROM fauna
WHERE name LIKE '%fish%';

SELECT * FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT * FROM fauna
WHERE discovery_date IS NULL;

SELECT * FROM fauna
WHERE discovery_date < '1950-01-01';