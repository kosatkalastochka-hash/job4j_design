CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

CREATE
OR REPLACE PROCEDURE delete_data()
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        DELETE FROM products AS p
		WHERE p.count = 0;
    END
$$;

CALL delete_data();

CREATE
OR REPLACE FUNCTION f_delete_data(d_id integer)
RETURNS void
LANGUAGE 'plpgsql'
AS
$$
    BEGIN
       DELETE FROM products AS p
		WHERE p.id = d_id;
    END;
$$;

SELECT f_delete_data(2);
