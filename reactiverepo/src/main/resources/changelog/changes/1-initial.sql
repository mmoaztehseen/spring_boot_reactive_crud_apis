-- liquibase formatted sql
-- changeset Moaz:create-schema runOnChange:true
CREATE TABLE IF NOT EXISTS customer (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), phone LONG, email VARCHAR(255), address VARCHAR(255), city VARCHAR(255), state VARCHAR(255), country VARCHAR(255), PRIMARY KEY (id));