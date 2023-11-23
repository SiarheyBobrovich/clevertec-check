--liquibase formatted sql
--changeset sbobrovich:insert-data-goods
INSERT INTO public.product (description, price, quantity_in_stock, wholesale_goods)
VALUES ('Milk', 1.07, 10, true),
       ('Cream 400g', 2.71, 20, true),
       ('Yogurt 400g', 2.10, 7, true),
       ('Packed potatoes 1kg', 1.47, 30, false),
       ('Packed cabbage 1kg', 1.19, 15, false),
       ('Packed tomatoes 350g', 1.60, 50, false),
       ('Packed apples 1kg', 2.78, 18, false),
       ('Packed oranges 1kg', 3.20, 12, false),
       ('Packed bananas 1kg', 1.10, 25, true),
       ('Packed beef fillet 1kg', 12.8, 7, false),
       ('Packed pork fillet 1kg', 8.52, 14, false),
       ('Packed chicken breasts 1kgSour ', 10.75, 18, false),
       ('Baguette 360g', 1.30, 10, true),
       ('Drinking water 1,5l', 0.80, 100, false),
       ('Olive oil 500ml', 5.30, 16, false),
       ('Sunflower oil 1l', 1.20, 12, false),
       ('Chocolate Ritter sport 100g', 1.10, 50, true),
       ('Paulaner 0,5l', 1.10, 100, false),
       ('Whiskey Jim Beam 1l', 13.99, 30, false),
       ('Whiskey Jack Daniels 1l', 17.19, 20, false);
