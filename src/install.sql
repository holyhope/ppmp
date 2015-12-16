create database online_cv;

CREATE TABLE IF NOT EXISTS users
    id INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    last_name VARCHAR(255) NULL,
    first_name VARCHAR(255) NULL,
    birthdate DATE NULL,
    email VARCHAR(255) NOT NULL,
    photo_path VARCHAR(255) NULL;
    
    