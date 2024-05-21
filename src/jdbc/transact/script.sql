 
 
use SampleDB; 
CREATE TABLE orders (
  order_id int(11) NOT NULL AUTO_INCREMENT,
  product_id int(11) DEFAULT NULL,
  amount float DEFAULT NULL,
  order_date datetime DEFAULT NULL,
  PRIMARY KEY (order_id)
) ;
CREATE TABLE products (
  product_id int(11) NOT NULL AUTO_INCREMENT,
  product_name varchar(45) NOT NULL,
  price float DEFAULT NULL,
  PRIMARY KEY (product_id)
);  

 CREATE TABLE monthly_sales ( 
    report_month int(11),
    product_id int(11),
    total_amount float  
);
