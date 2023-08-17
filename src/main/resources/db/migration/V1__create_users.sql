use uthra_boopathy_corejava_project;
 
 -------  USER  TABLE  -------
 
CREATE TABLE if NOT EXISTS users(
`user_id` INT AUTO_INCREMENT PRIMARY KEY,
`email` VARCHAR (50) UNIQUE NOT NULL,
`user_name` VARCHAR(20),
`age` INT,
`phone_number` VARCHAR(10),
`password` VARCHAR(20) ,
`role` VARCHAR(10),
`is_active` BOOLEAN DEFAULT TRUE,
`created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`modified_at`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DESCRIBE users;

-------- CREATE USERS ---------

INSERT INTO users (`email`, `user_name`, `age`, `phone_number`, `password`, `role`)
VALUES 
  ('uthra@gmail.com', 'Uthra', 18, '9587675763', 'Uthra@12', 'buyer'),
  ('ramya@gmail.com', 'Ramya', 23, '8956498348', 'Ramya@08', 'seller'),
  ('deepika@gamil.com', 'Deepika', 18, '9784231267', 'Deepika@23', 'buyer');
  
-------- UPDATE USERS --------

UPDATE users 
SET user_name = "Uthra Kannan" 
WHERE is_active = 1 AND user_id = 1;

-------- DELETE USERS ---------

UPDATE users
SET is_active = 0
WHERE is_active = 1 AND user_id = 3;

------ USER LIST ------

SELECT *
FROM users;

--------  CATEGORY TABLE  --------

CREATE TABLE IF NOT EXISTS categories(
  `cate_id` INT AUTO_INCREMENT PRIMARY KEY,
  `cate_name` VARCHAR(50) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE
);

DESCRIBE categories;

-------- CREATE CATEGORY --------

INSERT INTO categories (`cate_name`)
VALUES 
  ('Dress'),
  ('Jewells');
  
  --------  UPDATE CATEGORY  --------
  
  UPDATE categories
  SET cate_name = 'Sandal'
  WHERE is_active = 1 AND cate_id = 1;
  
  -------- DELETE CATEGORY  --------

UPDATE categories
SET is_active = 0
WHERE is_active = 1 AND cate_id = 2;

-------- CATEGORY LIST ------

SELECT *
FROM categories;

--------  TYPE TABLE  --------

CREATE TABLE IF NOT EXISTS types(
  `type_id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(50) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE,
  `cate_id` INT,
  FOREIGN KEY (`cate_id`) REFERENCES categories(`cate_id`)
);

-------- CREATE TYPES --------

INSERT INTO types (`name`, `cate_id`)
VALUES 
  ('Saree', 1),
  ('Croptop', 1),
  ('Chudi', 1);
  
  -------  UPDATE TYPE  --------
  
  UPDATE types
  SET name = 'Jeans'
  WHERE is_active = 1 AND type_id = 1;
  
  -------- DELETE TYPE  --------

UPDATE types
SET is_active = 0
WHERE is_active = 1 AND type_id = 2;

-------- TYPES LIST ------

SELECT *
FROM types;

DESCRIBE types;

--------  SIZE  TABLE  --------

CREATE TABLE IF NOT EXISTS sizes(
  `size_id` INT AUTO_INCREMENT PRIMARY KEY,
  `size_name` VARCHAR(50) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE
);

DESCRIBE sizes;

-------- CREATE SIZES --------

INSERT INTO sizes (`size_name`)
VALUES 
  ('S'),
  ('M'),
  ('L'),
  ('XL');
  
  -------- DELETE SIZE  --------

UPDATE sizes
SET is_active = 0
WHERE is_active = 1 AND size_id = 2;

-------- SIZE LIST ------

SELECT *
FROM sizes;

--------  PRODUCT  TABLE  --------

CREATE TABLE IF NOT EXISTS products(
  `pdt_id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE,
  `seller_id` INT,
  FOREIGN KEY (`seller_id`) REFERENCES users(`user_id`),
  `type_id` INT,
  FOREIGN KEY (`type_id`) REFERENCES types(`type_id`)
);

DESCRIBE products;

-------- CREATE PRODUCTS -----------

INSERT INTO products (`name`, `description`, `seller_id`, `type_id`)
VALUES 
  ('Product A', 'This is the description of Product A', 2, 1),
  ('Product B', 'This is the description of Product B', 2, 1);
  
-------- UPDATE PRODUCTS  -------

UPDATE products
SET name = "Saree"
WHERE is_active = 1 AND pdt_id = 1;

-------- DELETE PRODUCTS  --------

UPDATE products
SET is_active = 0
WHERE is_active = 1 AND pdt_id = 2;

-------- PRODUCT LIST ------

SELECT *
FROM products;

--------  PRICE TABLE  ----------

CREATE TABLE IF NOT EXISTS prices(
  `price_id` INT AUTO_INCREMENT PRIMARY KEY,
  `start_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `end_date`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` BOOLEAN DEFAULT TRUE,
  `actual_price` DECIMAL(10, 2) NOT NULL,
  `discount` DECIMAL(10, 2) NOT NULL,
  `current_price` DECIMAL(10, 2) NOT NULL,
  `pdt_id` INT,
  FOREIGN KEY (`pdt_id`) REFERENCES products(`pdt_id`),
  `size_id` INT, 
  FOREIGN KEY (`size_id`) REFERENCES sizes(`size_id`)
);

DESCRIBE prices;

-------- CREATE PRICES ---------

INSERT INTO prices (`actual_price`, `discount`, `current_price`, `pdt_id`, `size_id`)
VALUES 
  (657, 45, (657*(45/100)), 1, null),
  (9798, 25, (9798*(25/100)), 1, 1),
  (261, 15, (261*(15/100)), 2, 2);
  
-------- UPDATE PRICES --------

INSERT INTO prices (`actual_price`, `discount`, `current_price`, `pdt_id`, `size_id`, `end_date`)
VALUES 
  (7874, 25, (7874*(25/100)), 1, 2, null);

UPDATE prices
SET end_date = CURRENT_TIMESTAMP(), is_active = 0
WHERE is_active = 1 AND price_id = 2;

-------- DELETE PRICE ---------

UPDATE prices
SET is_active = 0
WHERE is_active = 1 AND price_id = 3;

-------- PRICE LIST ------

SELECT *
FROM prices;


