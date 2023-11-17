--liquibase formatted sql
--changeset sbobrovich:create-table-goods
CREATE TABLE public.goods
(
    id                BIGSERIAL      NOT NULL PRIMARY KEY UNIQUE,
    description       VARCHAR(50)    NOT NULL UNIQUE,
    price             DECIMAL(20, 2) NOT NULL CHECK ( price >= 0 ),
    quantity_in_stock INTEGER CHECK ( quantity_in_stock >= 0 ),
    wholesale_goods   BOOLEAN        NOT NULL default false
);
