 
use SampleDB;
 CREATE TABLE authors (
    author_id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL, 
    email varchar(45) NOT NULL,
    PRIMARY KEY (author_id)
);

CREATE TABLE books (
    book_id int(11) NOT NULL AUTO_INCREMENT,
    title varchar(200) NOT NULL,
    description varchar(200) NOT NULL,
    published date  NULL,
    author_id int(11) NULL,
    price float,
    rating int(11),
    PRIMARY KEY (book_id)
);


CREATE PROCEDURE Create_author  (IN name VARCHAR(45), email VARCHAR(45))
BEGIN
    DECLARE newAuthorID INT;
    INSERT INTO authors (name, email) VALUES (name, email);
     
    SET newAuthorID = (SELECT author_id FROM authors a WHERE a.name = name);
     
    INSERT INTO books (title, description, published, author_id, price, rating)
        VALUES (CONCAT('Life Story of ', name),
                CONCAT('Personal Stories of ', name),
                date('2016-12-30'), newAuthorID, 10.00, 0);
    
END


CREATE PROCEDURE get_books(IN rate INT)
BEGIN
    SELECT * FROM books WHERE rating >= rate;
END
-- Cac tham so phai viet tren 1 dongs
CREATE PROCEDURE summary_report( IN title1 VARCHAR(45), OUT totalBooks INT, OUT totalValue DOUBLE,  OUT highPrice DOUBLE )
BEGIN
    DECLARE maxPrice DOUBLE;
     
    SELECT COUNT(*) AS bookCount, SUM(price) as total
        FROM books b JOIN authors a ON b.author_id = a.author_id
        AND b.title LIKE CONCAT('%', title, '%')
    INTO totalBooks, totalValue;
     
     
    SELECT MAX(price) FROM books WHERE price INTO maxPrice;
     
    IF (maxPrice > -1) THEN
        SET highPrice = maxPrice;
    END IF;
END

 
