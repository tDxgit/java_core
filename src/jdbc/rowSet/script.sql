  
use SampleDB;  
 CREATE TABLE student (
    student_id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL, 
    email varchar(45) NOT NULL,
    major varchar(45) NOT NULL ,
    PRIMARY KEY (student_id)
);