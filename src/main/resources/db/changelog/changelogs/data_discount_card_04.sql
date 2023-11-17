--liquibase formatted sql
--changeset sbobrovich:insert-data-discount-card
INSERT INTO public.discount_card (number, amount)
VALUES (1111, 3),
       (2222, 3),
       (3334, 4),
       (4444, 5)
