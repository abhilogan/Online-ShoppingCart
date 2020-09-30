CREATE TABLE category (
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_1.png', true);

INSERT INTO category (name, description,image_url,is_active) VALUES ('Television', 'This is description for Television category!', 'CAT_2.png', true);

INSERT INTO category (name, description,image_url,is_active) VALUES ('Mobile', 'This is description for Mobile category!', 'CAT_3.png', true);



CREATE TABLE user_detail(

	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),
	CONSTRAINT pk_user_id PRIMARY KEY(id) 
);

INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number)
VALUES ('Pranay','Singh','USER','true','$2b$10$MqGs9O/4a2HVlENtLNISZ.J1muFtxF1yNwdlY.BNCi4CYL3kunkJO','ps@gmail.com','9806871458');

INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number)
VALUES ('Abhishek','Verma','ADMIN','true','$2b$10$cVcMlGjVng6zj7GgpRyj6.ab8WBPG7VSIw.YMu0NPmDqGJz6Fm4y','av@gmail.com','8770192009');

INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number)
VALUES ('Ashutosh','Bishnoi','SUPPLIER','true','$2b$10$9tn/CNuh95oAI9DO4kv0ne7EPJQQ1DPV9wsmTVgro08Q2i3/bxQK','ab@gmail.com','9806546213');


CREATE TABLE product(

	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY(id),
	CONSTRAINT fk_product_catgory_id FOREIGN KEY (category_id) REFERENCES category(id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id) 
); 

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 3, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 32000, 2, true, 3, 3, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 3, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 1, 3, 0, 0 );
