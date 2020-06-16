DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(4000) NOT NULL,
  price int DEFAULT 0
);

INSERT INTO product (name, description, price) VALUES
  ('Product 1', 'Description 1', 50000),
  ('Product 2', 'Description 2', 60000),
  ('Product 3', 'Description 3', 70000),
  ('Product 4', 'Description 4', 80000),
  ('Product 5', 'Description 5', 90000),
  ('Product 6', 'Description 6', 10000),
  ('Product 7', 'Description 7', 20000),
  ('Product 8', 'Description 8', 30000);