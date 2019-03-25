BEGIN TRANSACTION;

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Ground Beef 80% Lean', 'Meat', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Potato - Russet', 'Produce', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Lettuce - Iceberg', 'Produce', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('White Hamburger Buns', 'Bread', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Salt Shaker', 'Spices', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Pepper Shaker', 'Spices', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('American Cheese Singles', 'Dairy', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Restaurant Style Tortilla Chips', 'Snacks', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Chunky Tradtional Salsa - Mild', 'Snacks', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Squash - Spaghetti', 'Produce', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Pasta Linguine', 'Pasta', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Salted Butter Sticks', 'Dairy', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Extra Virgin Olive Oil', 'Baking', 'Kroger', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Grated Pecorino Romano', 'Deli', 'Murrays', 1);

INSERT INTO product (product_description, commodity, brand, product_size)
VALUES ('Shredded Parmesan Cheese Cup', 'Deli', 'Private Selection', 1);

INSERT INTO recipe (name, time_to_cook, recipe_instructions, recipe_description, recipe_video, recipe_image, recipe_price)
VALUES ('Cheeseburger', 45, '1. Make fries: Preheat oven to 425°F. Line a large baking pan with foil and mist foil with cooking spray. In a large bowl, toss potatoes with 1/2 tsp. salt and 1/4 tsp. pepper. | 2. Set potatoes in a single layer on baking sheet. Bake until lightly browned around edges, about 15 minutes. Turn potatoes and bake until softened and browned, about 20 minutes longer. Sprinkle with additional salt and pepper. | 3. Make burgers: Preheat broiler to high or grill to medium. Line a broiling pan with foil and mist with cooking spray. | 4. Add ground beef to a bowl, add salt and pepper. Gently blend and shape into two round, flat patties. | 5. Broil or grill burgers until a meat thermometer registers 160°F, about 5 minutes per side. Serve burgers with lettuce and tomato on toasted hamburger buns, with fries on the side. Offer ketchup, mustard and other condiments, if desired.', 'American Style Cheeseburgers with Fries', 'https://www.youtube.com/embed/CJj84y5P3_g', 'https://i.postimg.cc/ZR6CnD79/burger.jpg', 11.08);

INSERT INTO recipe (name, time_to_cook, recipe_instructions, recipe_description, recipe_image, recipe_price)
VALUES ('Chips and Salsa', 1, '1. Open jar of salsa. | 2. Pour salsa into bowl. | 3. Open bag of tortilla chips and dig in!', 'Party Style Chips and Salsa', 'https://i.postimg.cc/Wb5tdZn7/chipsandsalsa.jpg', 2.74);

INSERT INTO recipe (name, time_to_cook, recipe_instructions, recipe_description, recipe_video ,recipe_image, recipe_price)
VALUES ('Spaghetti Squash', 40, '1. Preheat the oven to 375°F and halve squash lengthwise. Use a spoon to scoop out and discard seeds from the middle of each half. | 2. Arrange squash in a 9x13-inch casserole dish, cut-side down. Pour 1/2 cup water into the dish and bake until just tender, 30 to 35 minutes. | 3. Rake a fork back and forth across the squash to remove its flesh in strands…like spaghetti!', 'Spaghetti Squash', 'https://www.youtube.com/embed/Rodcf1BnYZU', 'https://i.postimg.cc/0jc5JgpN/spaghetti-Squash.jpg', 3.87);

INSERT INTO recipe (name, time_to_cook, recipe_instructions, recipe_description, recipe_image, recipe_price)
VALUES ('Cacio e Pepe', 25, '1. In a pot of boiling salted water, cook pasta according to package instructions until al dente. Reserve 2/3 cup pasta water and drain pasta. | 2. In a large skillet over medium heat, melt 1 tablespoon butter with oil. Add a generous amount of black pepper and toast until fragrant, about 1 minute. | 3. Add 1/3 cup reserved pasta water and bring to simmer. Whisk in butter then, using tongs, toss pasta into butter mixture. | 4. Add cheeses and toss constantly until cheese is melty, removing skillet from heat when about half the cheese has melted. (If sauce is too thick, loosen with more pasta water.)', 'Cacio e Pepe', 'https://i.postimg.cc/zBwG8HvK/pasta.jpg', 18.94);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,1,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,2,2);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,3,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,4,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,5,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,6,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (1,7,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (2,8,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (2,9,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (3,10,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (4,11,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (4,12,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (4,13,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (4,14,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (4,15,1);

INSERT INTO recipe_product (recipe_ID, upc, quantity)
VALUES (4,6,1);

INSERT INTO category (name)
VALUES ('American');

INSERT INTO category (name)
VALUES ('Vegetarian');

INSERT INTO category (name)
VALUES ('Italian');

INSERT INTO category (name)
VALUES ('Mexican');

INSERT INTO recipe_category (recipe_ID, category_ID)
VALUES (1, 1);

INSERT INTO recipe_category (recipe_ID, category_ID)
VALUES (2, 4);

INSERT INTO recipe_category (recipe_ID, category_ID)
VALUES (3, 2);

INSERT INTO recipe_category (recipe_ID, category_ID)
VALUES (4, 3);

INSERT INTO store (store_zip)
VALUES ('45069');

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 1, 4.29, 4.29);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 2, 0.69, 0.69);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 3, 1.49, 1.49);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 4, 1.25, 1.00);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 5, 0.49, 0.49);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 6, 0.49, 0.49);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 7, 1.69, 1.69);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 8, 1.25, 1.25);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 9, 1.49, 1.49);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 10, 3.87, 3.87);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 11, 1.49, 1.00);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 12, 3.29, 3.29);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 13, 4.69, 4.69);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 14, 4.99, 4.99);

INSERT INTO product_store (store_ID, upc, product_price, product_kroger_plus_price)
VALUES (1, 15, 3.99, 3.99);

INSERT INTO customer (username, firstname, lastname, email_address, password)
VALUES ('user', 'Tracy', 'McGrady', 'magicsRealNumberOne@gmail.com', 'password123');

INSERT INTO customer_pantry (customer_id)
VALUES (1);

INSERT INTO pantry_products (pantry_id, upc)
VALUES (1,5), (1,6), (1, 7);

INSERT INTO pantry_products (pantry_id, upc)
VALUES (1,12), (1,13);

INSERT INTO customer_cart (customer_id)
VALUES (1);


COMMIT TRANSACTION;