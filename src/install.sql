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
	
CREATE TABLE IF NOT EXISTS school(
	id INT(11) NOT NULL,
	label VARCHAR(255) NULL,
	location VARCHAR(255) NULL,
	type VARCHAR(255) NULL,
	PRIMARY KEY(id)
	)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS study(
	id_users INT(11) NOT NULL,
	id_school INT(11) NOT NULL,
	year_start DATE,
	year_end DATE,
	PRIMARY KEY(id_users, id_school),
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_school) REFERENCES school(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS diploma(
	id INT(11) NOT NULL,
	label VARCHAR(255),
	specialization VARCHAR(255),
	PRIMARY KEY(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS obtain(
	id_users INT(11),
	id_school INT(11),
	id_diploma INT(11),
	year DATE NOT NULL,
	PRIMARY KEY(id_users, id_school, id_diploma),
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_diploma) REFERENCES diploma(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS hobby(
	id INT(11) NOT NULL,
	label VARCHAR(255),
	description TEXT,
	PRIMARY KEY(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS practice(
	id_users INT(11),
	id_hobby INT(11),
	PRIMARY KEY(id_users, id_hobby),
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_hobby) REFERENCES hobby(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS skill(
	id INT(11),
	label VARCHAR(255),
	description TEXT,
	PRIMARY KEY(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS know(
	id_users INT(11),
	id_skill INT(11),
	PRIMARY KEY(id_users, id_skill),
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_skill) REFERENCES skill(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS company(
	id INT(11),
	label VARCHAR(255),
	description TEXT,
	year_start DATE NOT NULL,
	year_end DATE,
	domain VARCHAR(255),
	size INT(5),
	location VARCHAR(255),
	PRIMARY KEY(id)
)ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS experiences(
	id_users INT(11),
	id_company INT(11),
	label VARCHAR(255),
	description TEXT,
	year_start DATE,
	year_end DATE,
	PRIMARY KEY(id_users,id_company),
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_company) REFERENCES company(id)
)ENGINE=InnoDB CHARACTER SET=utf8;