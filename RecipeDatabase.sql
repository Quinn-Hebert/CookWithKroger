DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS recipe_category;
DROP TABLE IF EXISTS recipe_product;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS product_store;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS products_bought_recently;

BEGIN TRANSACTION;

CREATE TABLE product
(
    upc SERIAL,
    product_description VARCHAR(128) NOT NULL,
    commodity VARCHAR(64) NOT NULL,
    brand VARCHAR(64) NOT NULL,
    product_size INT NOT NULL,

    CONSTRAINT pk_product PRIMARY KEY (upc)
);

CREATE TABLE recipe
(
    recipe_ID SERIAL,
    name VARCHAR(64) NOT NULL,
    recipe_instructions VARCHAR(1000) NOT NULL,
    recipe_description VARCHAR(128) NOT NULL,
    time_to_cook int NOT NULL,
    recipe_video VARCHAR(200),
    recipe_image VARCHAR(200),
    recipe_price FLOAT NOT NULL,

    CONSTRAINT pk_recipe PRIMARY KEY (recipe_ID)
);

CREATE TABLE category
(
    category_ID SERIAL,
    name VARCHAR(64),

    CONSTRAINT pk_category PRIMARY KEY (category_ID)
);

CREATE TABLE recipe_category
(
    category_ID INT NOT NULL,
    recipe_ID INT NOT NULL,

    CONSTRAINT pk_recipe_category PRIMARY KEY (category_ID, recipe_ID),
    CONSTRAINT fk_category FOREIGN KEY (category_ID) REFERENCES category (category_ID),
    CONSTRAINT fk_recipe FOREIGN KEY (recipe_ID) REFERENCES recipe (recipe_ID)
);

CREATE TABLE recipe_product
(
    recipe_ID INT NOT NULL,
    upc INT NOT NULL,
    quantity INT NOT NULL,

    CONSTRAINT pk_recipe_product PRIMARY KEY (recipe_ID, upc),
    CONSTRAINT fk_product FOREIGN KEY (upc) REFERENCES product (upc),
    CONSTRAINT fk_recipe FOREIGN KEY (recipe_ID) REFERENCES recipe (recipe_ID)
);

CREATE TABLE store
(
    store_ID SERIAL,
    store_zip VARCHAR(24) NOT NULL,

    CONSTRAINT pk_store PRIMARY KEY (store_ID)
);

CREATE TABLE product_store
(
    upc INT NOT NULL,
    store_ID INT NOT NULL,
    product_price FLOAT NOT NULL,
    product_kroger_plus_price FLOAT,

    CONSTRAINT pk_product_store PRIMARY KEY (upc, store_ID),
    CONSTRAINT fk_product FOREIGN KEY (upc) REFERENCES product (upc),
    CONSTRAINT fk_store FOREIGN KEY (store_ID) REFERENCES store (store_ID)
);

CREATE TABLE customer
(
    customer_ID SERIAL,
    userName VARCHAR(64) NOT NULL,
    firstName VARCHAR(64) NOT NULL,
    lastName VARCHAR(64) NOT NULL,
    email_address VARCHAR(64),
    password VARCHAR(64),

    CONSTRAINT pk_customer PRIMARY KEY (customer_ID)
);

CREATE TABLE customer_pantry
(
    pantry_ID SERIAL,
    customer_ID INT NOT NULL,

    CONSTRAINT pk_customer_pantry PRIMARY KEY (pantry_ID),
    CONSTRAINT fk_customer FOREIGN KEY (customer_ID) REFERENCES customer (customer_ID)
);

CREATE TABLE pantry_products
(
    pantry_ID INT NOT NULL,
    upc INT NOT NULL,

    CONSTRAINT pk_pantry_products PRIMARY KEY (pantry_ID, upc),
    CONSTRAINT fk_customer FOREIGN KEY (pantry_ID) REFERENCES customer_pantry (pantry_ID),
    CONSTRAINT fk_product FOREIGN KEY (upc) REFERENCES product (upc)
);

CREATE TABLE customer_cart
(
    cart_ID SERIAL,
    customer_ID INT NOT NULL,

    CONSTRAINT pk_customer_cart PRIMARY KEY (cart_ID),
    CONSTRAINT fk_customer FOREIGN KEY (customer_ID) REFERENCES customer (customer_ID)
);

CREATE TABLE cart_products
(
    cart_ID INT NOT NULL,
    upc INT NOT NULL,
    quantity INT NOT NULL,

    CONSTRAINT pk_cart_products PRIMARY KEY (cart_ID, upc),
    CONSTRAINT fk_customer FOREIGN KEY (cart_ID) REFERENCES customer_cart (cart_ID),
    CONSTRAINT fk_product FOREIGN KEY (upc) REFERENCES product (upc)
);

COMMIT TRANSACTION;
