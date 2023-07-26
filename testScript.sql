USE testSchema;

CREATE TABLE IF NOT EXISTS testTable(
	ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(30)
);

INSERT INTO testTable(name) VALUES ("TEST DATA HERE");

SELECT * FROM testTable;