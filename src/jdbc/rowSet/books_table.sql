
CREATE DATABASE example_db;
SHOW DATABASES;
USE example_db;
CREATE TABLE books_table (title VARCHAR(40),
 author VARCHAR(40), PRIMARY KEY (title));
INSERT INTO books_table VALUES ('A', 'Christie');
INSERT INTO books_table VALUES ('B', 'Dumas');
INSERT INTO books_table VALUES ('C', 'Conan');
INSERT INTO books_table VALUES ('B', 'Boris');
INSERT INTO books_table VALUES ('Animal','George');
 