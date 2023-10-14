USE uthra_boopathy_corejava_project;
 
 -------  USER  TABLE  -------
 
CREATE TABLE if NOT EXISTS users(
`user_id` INT AUTO_INCREMENT PRIMARY KEY,
`email` VARCHAR (50) UNIQUE NOT NULL,
`user_name` VARCHAR(500) NOT NULL,
`age` INT NOT NULL,
`phone_number` VARCHAR(10) NOT NULL UNIQUE,
`password` VARCHAR(500) NOT NULL,
`role` VARCHAR(10) NOT NULL,
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
  ('Art Silk Saree', 'https://iili.io/HNO3V94.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 1),
  ('Casual Regular Sleeves croptop', 'https://iili.io/HNeBZmJ.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 2),
  ('Girls Slim Mid Rise Blue Jeans', 'https://iili.io/HNeowgI.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 3),
  ('Semi Stitched Lehenga Choli', 'https://iili.io/HNeTXTX.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 4),
  ('Viscose Women Churidar', 'https://iili.io/HNeJ0rJ.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 5),
  ('Casual, party clutch', 'https://iili.io/J9C5DJI.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 6),
  ('Chain', 'https://iili.io/H8BIwy7.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 7),
  (' Hand-held Bag', 'https://iili.io/J9C7HWG.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 8),
  ('Bracelets', 'https://iili.io/H8BIJLb.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 9),
  ('Pendent set', 'https://iili.io/H8BIjvS.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 10),
  ('Earring', 'https://iili.io/HvZYTHN.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 11),
  ('Jewell set', 'https://iili.io/HvZYPsa.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 12),
  ('Ring', 'https://iili.io/HvZY6Wg.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 13),
  ('Choker', 'https://iili.io/H8BzSdN.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 14),
  ('Necklace', 'https://iili.io/HvZYVO7.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 15),
  ('Flats Sandal', 'https://iili.io/HvZY7xn.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 16),
  ('Heels Sandal', 'https://iili.io/HvZYaiG.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 17),
  ('Sling Back Flats For Girls', 'https://iili.io/HvZYkUQ.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 18),
  ('Bellies For Women ', 'https://iili.io/J9Cc9zF.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 19),
  ('HURRICANE Running Shoes', 'https://iili.io/J9CY8wF.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 20),
  ('Non-Transfer Longlasting Liquid Matte Lipstick', 'https://iili.io/HvZY1Ul.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 21),
  (' Waterproof and Anti-mudging kajal', 'https://iili.io/HvZYCiJ.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 22),
  ('LOréal Paris Glycolic Bright Day Cream', 'https://iili.io/HvZYuRI.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 23),
  ('SKINPLUS brush applicator', 'https://iili.io/HvmbfgR.webp', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 24),
  ('Analog Watch', 'https://iili.io/J9CcQQn.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 25),
  ('Black Strap Electronic Digital Watch', 'https://iili.io/J9ClHpS.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 26),
  ('Leather Strap Combo Watch', 'https://iili.io/J9CluZF.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 27),
  ('Banarasi Silk Blend Saree', 'https://iili.io/J9ClvuS.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 28),
  ('Sambalpuri Silk Blend Saree', 'https://iili.io/J9C0RNS.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 29),
  ('Kanjivaram Pure Silk, Art Silk Saree', 'https://iili.io/J9C0xKG.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 30),
  ('Chanderi, Cotton Silk Saree', 'https://iili.io/J9C0vUv.jpg', 'Elevate your style with our stunning Floral Print A-Line Midi Dress. This dress is the epitome of timeless elegance and modern charm, making it the perfect addition to your wardrobe.', 2, 31);
  
  
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
  (1478, 81, (1478-(1478*(81/100))), 1),
  (1299, 69, (1299-(1299*(69/100))), 2),
  (1848, 79, (1848-(1848*(79/100))), 3),
  (1478, 81, (1478-(1478*(81/100))), 4),
  (1499, 70, (1499-(1499*(70/100))), 5),
  (1499, 70, (1499-(1499*(70/100))), 6),
  (1599, 67, (1599-(1599*(67/100))), 7),
  (999, 64, (999-(999*(64/100))), 8),
  (2999, 60, (2999-(2999*(60/100))), 9),
  (2999, 80, (2999-(2999*(80/100))), 10),
  (2599, 68, (2599-(2599*(68/100))), 11),
  (599, 27, (599-(599*(27/100))), 12),
  (848, 39, (848-(848*(39/100))), 13),
  (999, 41, (999-(999*(41/100))), 14),
  (2799, 85, (2799-(2799*(85/100))), 15),
  (799, 13, (799-(799*(13/100))), 16),
  (1698, 62, (1698-(1698*(62/100))), 17),
  (1698, 72, (1698-(1698*(72/100))), 18),
  (3299, 47, (3299-(3299*(47/100))), 19),
  (1499, 84, (1499-(1499*(84/100))), 20),
  (1499, 70, (1499-(1499*(70/100))), 21),
  (1199, 71, (1199-(1199*(71/100))), 22),
  (1199, 64, (1199-(1199*(64/100))), 23),
  (4000, 56, (4000-(4000*(56/100))), 24),
  (9263, 58, (9263-(9263*(58/100))), 25),
  (1888, 64, (1888-(1888*(64/100))), 26),
  (2499, 79, (2499-(2499*(79/100))), 27),
  (1999, 74, (1999-(1999*(74/100))), 28),
  (1888, 76, (1888-(1888*(76/100))), 29),
  (2499, 73, (2499-(2499*(73/100))), 30),
  (999, 48, (999-(999*(48/100))), 31);

  
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

CREATE TABLE IF NOT EXISTS address(
  `address_id` INT AUTO_INCREMENT PRIMARY KEY,
  `start_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `modified_date`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` BOOLEAN DEFAULT TRUE,
  `address` VARCHAR(1000) NOT NULL,
  `state` VARCHAR(100) NOT NULL,
  `pincode` INT NOT NULL,
  `set_as_default` BOOLEAN,
  `name`  VARCHAR(100) NOT NULL,
  `user_id` INT,
  FOREIGN KEY (`user_id`) REFERENCES users(`user_id`)
);

INSERT INTO address(`name`, `address`, `state`, `pincode`, `set_as_default`, `user_id`)
VALUES ('Uthra', '34 B, Melaputhu street, Ambasamudram', 'Tamil Nadu', '627401', 1, 1);

CREATE TABLE IF NOT EXISTS orders(
  `order_id` INT AUTO_INCREMENT PRIMARY KEY,
  `ordered_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `cancel_date`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delivery_date` DATE DEFAULT NULL,
  `is_active` BOOLEAN DEFAULT TRUE,
  `phone_number` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL,
  `payment` VARCHAR(100) DEFAULT 'CASH ON DELIVERY',
  `status` ENUM('WAITING_LIST', 'ON_THE_WAY', 'DELIVERED', 'CANCELLED') NOT NULL DEFAULT 'WAITING_LIST',
  `user_id` INT,
  FOREIGN KEY (`user_id`) REFERENCES users(`user_id`),
  `seller_id` INT,
  FOREIGN KEY (`seller_id`) REFERENCES products(`seller_id`),
  `address_id` INT,
  FOREIGN KEY (`address_id`) REFERENCES address(`address_id`),
  `price_id` INT,
  FOREIGN KEY (`price_id`) REFERENCES prices(`price_id`)
);


select * from categories;

select * from products;

select * from types;

select * from users;

select * from prices;

select * from address;

select * from orders;