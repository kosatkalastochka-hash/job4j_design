CREATE TABLE products
(   id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

CREATE OR REPLACE FUNCTION tax_statement_function() returns TRIGGER
AS $$
BEGIN
        UPDATE products
        SET price = price * 1.2
        WHERE id IN (SELECT id FROM inserted);
		RETURN NULL;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_statement_triger
AFTER INSERT
ON products
REFERENCING NEW TABLE AS inserted
FOR EACH STATEMENT
EXECUTE FUNCTION tax_statement_function();

CREATE OR REPLACE FUNCTION tax_each_row_function() returns TRIGGER
AS $$
BEGIN
      NEW.price =NEW.price * 1.2;
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER tax_each_row_triger
BEFORE INSERT
ON products
FOR EACH ROW
EXECUTE FUNCTION tax_each_row_function();

CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE OR REPLACE FUNCTION history_of_price_function() returns TRIGGER
AS $$
BEGIN
INSERT INTO history_of_price(name,price,date)
VALUES(NEW.name,NEW.price,now());
        RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER history_of_price_triger
AFTER INSERT
ON products
FOR EACH ROW
EXECUTE FUNCTION history_of_price_function();

