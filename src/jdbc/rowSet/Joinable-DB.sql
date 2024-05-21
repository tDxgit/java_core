CREATE TABLE emp_table 
(empid VARCHAR(3), name VARCHAR(30), PRIMARY KEY (empid));
CREATE TABLE emp_addr_table 
(empid VARCHAR(3), addrid VARCHAR(1), addr VARCHAR(30)
, city VARCHAR(20) , state VARCHAR(20), PRIMARY KEY (empid, addrid ));


INSERT INTO emp_table VALUES ('1', 'William');
INSERT INTO emp_table VALUES ('2', 'Valentina');

INSERT INTO emp_addr_table VALUES ('1', '1', '41 A Street', 'Phoenix', 'Arizona');
INSERT INTO emp_addr_table VALUES ('1', '2', '10 b Street', 'A','California');
INSERT INTO emp_addr_table VALUES ('2', '1', '12-E Street', 'New York', 'New York');
