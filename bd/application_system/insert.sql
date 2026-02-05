INSERT INTO roles(name,user_id)
VALUES('system_administrator',1),
('basic_rights',2),
('order_processing',2),
('working_with_files',3);

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

INSERT INTO comments(comment)
VALUES
('delivery_by_courier'),
( 'delivery_during_business_hours'),
('delivery by prepayment');

SELECT * FROM comments;

INSERT INTO attachs(file_path)
VALUES
('D:\wholesalers'),
( 'D:\retail'),
('D:\firm');

SELECT * FROM attachs;

INSERT INTO items(name, comments_id, attachs_id )
VALUES
('WH000-0001 from 01.02.2026',NULL,1),
('RE000-0005 from 01.01.2026',1,2),
('FI000-0007 from 23.01.2026',2,3);

SELECT * FROM items;

INSERT INTO  users(first_name,larst_name,items_id)
VALUES
('Ludmila','Shlyapkina',1),
('Ivan','Ivanov',2),
('Olga','Verstova',3);

SELECT * FROM users;

INSERT INTO  states(status,items_id)
VALUES
('to_be_agreed',1),
('in_reserve',2),
('for_shipment',3);

SELECT * FROM states;

INSERT INTO  categories(category,
items_id)
VALUES
('wholesale',1),
('retail',2),
('firm',3);

SELECT * FROM categories;
