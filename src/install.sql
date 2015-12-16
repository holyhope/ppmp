create database IF NOT EXISTS online_cv;
ALTER DATABASE online_cv CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE TABLE IF NOT EXISTS users(
    id INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    last_name VARCHAR(255) NULL,
    first_name VARCHAR(255) NULL,
    birthdate DATE NULL,
    email VARCHAR(255) NOT NULL,
    photo_path VARCHAR(255) NULL
    )ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS phone(
	id INT(11) NOT NULL,
	number VARCHAR(15) NULL,
	type VARCHAR(5) NULL,
	id_users INT(11),
	PRIMARY KEY(id),
	FOREIGN KEY (id_users) REFERENCES users(id)
	)ENGINE=InnoDB CHARACTER SET=utf8;    