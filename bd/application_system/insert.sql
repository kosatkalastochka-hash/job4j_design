INSERT INTO roles(name)
VALUES('system_administrator'),
('basic_rights'),
('order_processing'),
('working_with_files');

SELECT * FROM roles;

INSERT INTO rules(right_access)
VALUES('addition'),
('change'),
('deletion'),
('reading');

SELECT * FROM rules;

INSERT INTO roles_rules(roles_id, rules_id)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(2,2),
(2,4),
(3,2),
(3,4),
(4,2),
(4,4);

SELECT * FROM roles_rules;

INSERT INTO  users(first_name,larst_name,roles_id)
VALUES
('Ludmila','Shlyapkina',1),
('Ivan','Ivanov',2),
('Olga','Verstova',4);

SELECT * FROM users;

INSERT INTO  states(status)
VALUES
('to_be_agreed'),
('in_reserve'),
('for_shipment');

SELECT * FROM states;

INSERT INTO  categories(category)
VALUES
('wholesale'),
('retail'),
('firm');

SELECT * FROM categories;

INSERT INTO items(name, user_id, categories_id, states_id)
VALUES
('WH000-0001 from 01.02.2026',1,1,2),
('RE000-0005 from 01.01.2026',2,2,1),
('FI000-0007 from 23.01.2026',3,3,3);

SELECT * FROM items;

INSERT INTO comments(comment, items_id)
VALUES
('delivery_by_courier',1),
( 'delivery_during_business_hours',2),
('delivery by prepayment',3);

SELECT * FROM comments;

INSERT INTO attachs(file_path, items_id)
VALUES
('D:\wholesalers',1),
( 'D:\retail',2),
('D:\firm',3);

SELECT * FROM attachs;
