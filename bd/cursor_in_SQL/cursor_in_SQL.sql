BEGIN;
DECLARE
cursor_products SCROLL CURSOR FOR
SELECT * FROM products;

FETCH LAST FROM cursor_products;

MOVE BACKWARD 4 FROM cursor_products;

FETCH PRIOR FROM cursor_products;

MOVE BACKWARD 7 FROM cursor_products;

FETCH PRIOR FROM cursor_products;

MOVE BACKWARD 4 FROM cursor_products;

FETCH PRIOR FROM cursor_products;

FETCH PRIOR FROM cursor_products;

CLOSE cursor_products;

COMMIT;