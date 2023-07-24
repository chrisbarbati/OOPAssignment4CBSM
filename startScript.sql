USE oopassignment;

CREATE TABLE IF NOT EXISTS foods(
	ID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    calories int NOT NULL,
    isVegan bool NOT NULL,
    isGlutenFree bool NOT NULL,
    imagePath varchar(50)
);

CREATE TABLE IF NOT EXISTS meals(
	ID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    price double(15,2) NOT NULL,
    isVegan bool NOT NULL,
    isGlutenFree bool NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
	ID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	subtotal double(15,2) NOT NULL,
    taxRate double(15,2) NOT NULL,
    tips double(15,2) NOT NULL,
    total double(15,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS servers(
	ID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30),
    totalTips double(15,2)
);

SELECT * FROM foods;
SELECT * FROM meals;
SELECT * FROM orders;
SELECT * FROM servers;