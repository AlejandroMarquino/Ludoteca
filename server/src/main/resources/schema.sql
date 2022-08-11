DROP TABLE IF EXISTS CATEGORY;

CREATE TABLE CATEGORY (
  id BIGINT IDENTITY NOT NULL PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS AUTHOR;

CREATE TABLE AUTHOR (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(400) NOT NULL,
    nationality VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS GAME;

CREATE TABLE GAME (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    age VARCHAR(3) NOT NULL,
    category_id BIGINT DEFAULT NULL,
    author_id BIGINT DEFAULT NULL
);

ALTER TABLE GAME ADD FOREIGN KEY (category_id) REFERENCES CATEGORY(id);
ALTER TABLE GAME ADD FOREIGN KEY (author_id) REFERENCES AUTHOR(id);

DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE CUSTOMER (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS LEASING;

CREATE TABLE LEASING  (
	id BIGINT IDENTIRY NOT NULL PRIMARY KEY,
	game_id BIGINT NOT NULL,
	customer_id BIGINT NOT NULL,
	leasing_date DATE,
	end_date DATE
);
	
ALTER TABLE LEASING ADD FOREIGN KEY (game_id) REFERENCES GAME(id);
ALTER TABLE LEASING ADD FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id);