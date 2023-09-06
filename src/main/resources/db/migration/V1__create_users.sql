USE uthra_boopathy_corejava_project;
 
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
  `cate_name` VARCHAR(50) NOT NULL UNIQUE,
  `img_url` VARCHAR(500) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE
);

DESCRIBE categories;

-------- CREATE CATEGORY --------

INSERT INTO categories (`cate_name`, `img_url`)
VALUES 
  ('Dress', 'https://iili.io/J9CoLXV.webp'),
  ('Accessories', 'https://iili.io/J9Cxy67.webp'),
  ('Jewells', 'https://iili.io/HvZYL0v.webp'),
  ('Slippers', 'https://iili.io/HvZYe0x.webp'),
  ('Cosmetics', 'https://iili.io/HvZYGJ2.webp'),
  ('Watches', 'https://iili.io/J9CTC21.jpg'),
  ('Handloom sarees', 'https://iili.io/HNO3EcG.jpg');
  
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
  `name` VARCHAR(50) NOT NULL UNIQUE,
   `img_url` VARCHAR(500) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE,
  `cate_id` INT,
  FOREIGN KEY (`cate_id`) REFERENCES categories(`cate_id`)
);

-------- CREATE TYPES --------

INSERT INTO types (`name`, `img_url`, `cate_id`)
VALUES 
  ('Saree', 'https://iili.io/HNO3V94.jpg', 1),
  ('Croptop', 'https://iili.io/HNeBZmJ.webp', 1),
  ('Jeans', 'https://iili.io/HNeowgI.webp', 1),
  ('Leganga', 'https://iili.io/HNeTXTX.webp', 1),
  ('Chudi', 'https://iili.io/HNeJ0rJ.webp', 1),
   ('Clutches', 'https://iili.io/J9C5DJI.jpg', 2),
  ('Chains', 'https://iili.io/H8BIwy7.webp', 2),
  ('Handbags', 'https://iili.io/J9C7HWG.jpg', 2),
  ('Bracelets', 'https://iili.io/H8BIJLb.webp', 2),
  ('Pendant', 'https://iili.io/H8BIjvS.webp', 3),
  ('Earrings', 'https://iili.io/HvZYTHN.webp', 3),
   ('Jewell set', 'https://iili.io/HvZYPsa.webp', 3),
  ('Rings', 'https://iili.io/HvZY6Wg.webp', 3),
  ('Choker', 'https://iili.io/H8BzSdN.webp', 3),
  ('Necklaces', 'https://iili.io/HvZYVO7.webp', 3),
  ('Flats', 'https://iili.io/HvZY7xn.webp', 4),
   ('Heels', 'https://iili.io/HvZYaiG.webp', 4),
  ('Office wear', 'https://iili.io/HvZYkUQ.webp', 4),
  ('Casual shoes', 'https://iili.io/J9Cc9zF.jpg', 4),
  ('Sports shoe', 'https://iili.io/J9CY8wF.jpg', 4),
  ('Lipstick', 'https://iili.io/HvZY1Ul.webp', 5),
  ('Eye makeup', 'https://iili.io/HvZYCiJ.webp', 5),
  ('Face creams', 'https://iili.io/HvZYuRI.webp', 5),
  ('Brush', 'https://iili.io/HvmbfgR.webp', 5),
  ('Analogue', 'https://iili.io/J9CcQQn.jpg', 6),
  ('Digital', 'https://iili.io/J9ClHpS.jpg', 6),
  ('Leather', 'https://iili.io/J9CluZF.jpg', 6),
  ('Banarasi sari', 'https://iili.io/J9ClvuS.jpg', 7),
  ('Kanchipuram silk sari', 'https://iili.io/J9C0xKG.jpg', 7),
  ('Sambalpuri sari', 'https://iili.io/J9C0RNS.jpg', 7),
  ('Chanderi Silk', 'https://iili.io/J9C0vUv.jpg', 7);
  
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
  
  INSERT INTO sizes (`size_name`)
VALUES 
  ('null');
  
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
  `image_url` VARCHAR(500) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `is_active` BOOLEAN DEFAULT TRUE,
  `seller_id` INT,
  FOREIGN KEY (`seller_id`) REFERENCES users(`user_id`),
  `type_id` INT,
  FOREIGN KEY (`type_id`) REFERENCES types(`type_id`)
);

DESCRIBE products;

-------- CREATE PRODUCTS -----------

INSERT INTO products (`name`, `image_url`, `description`, `seller_id`, `type_id`)
VALUES 
  ('Art Silk Saree', 'https://iili.io/HNO3V94.jpg', 'This is the description of Product A', 2, 1),
  ('Casual Regular Sleeves croptop', 'https://iili.io/HNeBZmJ.webp', 'This is the description of Product A', 2, 2),
  ('Girls Slim Mid Rise Blue Jeans', 'https://iili.io/HNeowgI.webp', 'This is the description of Product A', 2, 3),
  ('Semi Stitched Lehenga Choli', 'https://iili.io/HNeTXTX.webp', 'This is the description of Product A', 2, 4),
  ('Viscose Women Churidar', 'https://iili.io/HNeJ0rJ.webp', 'This is the description of Product A', 2, 5),
  ('Casual, party clutch', 'https://iili.io/J9C5DJI.jpg', 'This is the description of Product A', 2, 6),
  ('Chain', 'https://iili.io/H8BIwy7.webp', 'This is the description of Product A', 2, 7),
  (' Hand-held Bag', 'https://iili.io/J9C7HWG.jpg', 'This is the description of Product A', 2, 8),
  ('Bracelets', 'https://iili.io/H8BIJLb.webp', 'This is the description of Product A', 2, 9),
  ('Pendent set', 'https://iili.io/H8BIjvS.webp', 'This is the description of Product A', 2, 10),
  ('Earring', 'https://iili.io/HvZYTHN.webp', 'This is the description of Product A', 2, 11),
  ('Jewell set', 'https://iili.io/HvZYPsa.webp', 'This is the description of Product A', 2, 12),
  ('Ring', 'https://iili.io/HvZY6Wg.webp', 'This is the description of Product A', 2, 13),
  ('Choker', 'https://iili.io/H8BzSdN.webp', 'This is the description of Product A', 2, 14),
  ('Necklace', 'https://iili.io/HvZYVO7.webp', 'This is the description of Product A', 2, 15),
  ('Flats Sandal', 'https://iili.io/HvZY7xn.webp', 'This is the description of Product A', 2, 16),
  ('Heels Sandal', 'https://iili.io/HvZYaiG.webp', 'This is the description of Product A', 2, 17),
  ('Sling Back Flats For Girls', 'https://iili.io/HvZYkUQ.webp', 'This is the description of Product A', 2, 18),
  ('Bellies For Women ', 'https://iili.io/J9Cc9zF.jpg', 'This is the description of Product A', 2, 19),
  ('HURRICANE Running Shoes', 'https://iili.io/J9CY8wF.jpg', 'This is the description of Product A', 2, 20),
  ('Non-Transfer Longlasting Liquid Matte Lipstick', 'https://iili.io/HvZY1Ul.webp', 'This is the description of Product A', 2, 21),
  (' Waterproof and Anti-mudging kajal', 'https://iili.io/HvZYCiJ.webp', 'This is the description of Product A', 2, 22),
  ('LOréal Paris Glycolic Bright Day Cream', 'https://iili.io/HvZYuRI.webp', 'This is the description of Product A', 2, 23),
  ('SKINPLUS brush applicator', 'https://iili.io/HvmbfgR.webp', 'This is the description of Product A', 2, 24),
  ('Analog Watch', 'https://iili.io/J9CcQQn.jpg', 'This is the description of Product A', 2, 25),
  ('Black Strap Electronic Digital Watch', 'https://iili.io/J9ClHpS.jpg', 'This is the description of Product A', 2, 26),
  ('Leather Strap Combo Watch', 'https://iili.io/J9CluZF.jpg', 'This is the description of Product A', 2, 27),
  ('Banarasi Silk Blend Saree', 'https://iili.io/J9ClvuS.jpg', 'This is the description of Product A', 2, 28),
  ('Sambalpuri Silk Blend Saree', 'https://iili.io/J9C0RNS.jpg', 'This is the description of Product A', 2, 29),
  ('Kanjivaram Pure Silk, Art Silk Saree', 'https://iili.io/J9C0xKG.jpg', 'This is the description of Product A', 2, 30),
  ('Chanderi, Cotton Silk Saree', 'https://iili.io/J9C0vUv.jpg', 'This is the description of Product B', 2, 31);
  
  
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
FROM users;

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
  FOREIGN KEY (`pdt_id`) REFERENCES products(`pdt_id`)
);

DESCRIBE prices;

-------- CREATE PRICES ---------

INSERT INTO prices (`actual_price`, `discount`, `current_price`, `pdt_id`)
VALUES 
  (9798, 25, (9798*(25/100)), 1),
  (9798, 25, (9798*(25/100)), 2),
  (9798, 25, (9798*(25/100)), 3),
  (9798, 25, (9798*(25/100)), 4),
  (9798, 25, (9798*(25/100)), 5),
  (9798, 25, (9798*(25/100)), 6),
  (9798, 25, (9798*(25/100)), 7),
  (9798, 25, (9798*(25/100)), 8),
  (9798, 25, (9798*(25/100)), 9),
  (9798, 25, (9798*(25/100)), 10),
  (9798, 25, (9798*(25/100)), 11),
  (9798, 25, (9798*(25/100)), 12),
  (9798, 25, (9798*(25/100)), 13),
  (9798, 25, (9798*(25/100)), 14),
  (9798, 25, (9798*(25/100)), 15),
  (9798, 25, (9798*(25/100)), 16),
  (9798, 25, (9798*(25/100)), 17),
  (9798, 25, (9798*(25/100)), 18),
  (9798, 25, (9798*(25/100)), 19),
  (9798, 25, (9798*(25/100)), 20),
  (9798, 25, (9798*(25/100)), 21),
  (9798, 25, (9798*(25/100)), 22),
  (9798, 25, (9798*(25/100)), 23),
  (9798, 25, (9798*(25/100)), 24),
  (9798, 25, (9798*(25/100)), 25),
  (9798, 25, (9798*(25/100)), 26),
  (9798, 25, (9798*(25/100)), 27),
  (9798, 25, (9798*(25/100)), 28),
  (9798, 25, (9798*(25/100)), 29),
  (9798, 25, (9798*(25/100)), 30),
  (261, 15, (261*(15/100)), 31);
  
-------- UPDATE PRICES --------

INSERT INTO prices (`actual_price`, `discount`, `current_price`, `pdt_id`)
VALUES 
  (7874, 25, (7874*(25/100)), 1);

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

select * from categories;

select * from products;

select * from types;

select * from users;

drop table sizes;