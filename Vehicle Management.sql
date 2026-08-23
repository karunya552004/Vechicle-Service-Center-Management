CREATE DATABASE vehicle_db;

USE vehicle_db;

CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    phone VARCHAR(15)
);

CREATE TABLE vehicles (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    vehicle_number VARCHAR(20),
    vehicle_model VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
select * from vehicles;

CREATE TABLE service_records (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    service_type VARCHAR(50),
    service_date DATE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);
select * from customers;