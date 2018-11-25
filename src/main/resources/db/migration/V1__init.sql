DROP TABLE IF EXISTS customers;
DROP SEQUENCE IF EXISTS customer_id_seq;
CREATE sequence customer_id_seq START WITH 1 INCREMENT BY 50;
CREATE TABLE customers (customer_id BIGINT NOT NULL,
                    first_name VARCHAR(255) NOT NULL ,
                    last_name VARCHAR(255) NOT NULL ,
                    PRIMARY KEY (customer_id));

DROP TABLE IF EXISTS accounts;
DROP SEQUENCE IF EXISTS account_id_seq;
CREATE SEQUENCE account_id_seq START WITH 1 INCREMENT BY 50;
CREATE TABLE accounts (account_id BIGINT NOT NULL,
                    version INTEGER,
                    balance DECIMAL NOT NULL,
                    customer_id bigint NOT NULL,
                    PRIMARY KEY (account_id),
                    FOREIGN KEY (customer_id) REFERENCES customers (customer_id));