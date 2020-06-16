DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS product_cart;

CREATE TABLE cart (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  accountId int NOT NULL,
  updatedDate date
);

CREATE TABLE product_cart (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cartId int NOT NULL,
  productId int NOT NULL,
  quantity int DEFAULT 1
);