INSERT INTO customers (customer_id, first_name, last_name) VALUES (14,	'Customer1',	'LastName');
INSERT INTO customers (customer_id, first_name, last_name) VALUES (15,	'Customer2',	'LastName');
INSERT INTO customers (customer_id, first_name, last_name) VALUES (16,	'Customer3',	'LastName');
INSERT INTO customers (customer_id, first_name, last_name) VALUES (17,	'Customer4',	'LastName');
INSERT INTO customers (customer_id, first_name, last_name) VALUES (18,	'Customer5',	'LastName');
INSERT INTO customers (customer_id, first_name, last_name) VALUES (19,	'Customer6',	'LastName');

INSERT INTO accounts (balance, version, customer_id, account_id) values (20000, 1, 14, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (50000, 1, 14, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (10000, 1, 15, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (60000, 1, 15, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (80000, 1, 16, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (90000, 1, 16, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (40000, 1, 17, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (30000, 1, 17, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (20000, 1, 18, nextval('account_id_seq'));
INSERT INTO accounts (balance, version, customer_id, account_id) values (40000, 1, 19, nextval('account_id_seq'));