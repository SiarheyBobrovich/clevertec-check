--liquibase formatted sql
--changeset sbobrovich:create-table-discount-card

CREATE TABLE public.discount_card
(
    id     BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
    number INTEGER   NOT NULL UNIQUE CHECK ( number >= 0 AND number <= 9999),
    amount SMALLINT  NOT NULL CHECK ( amount >= 0 AND amount <= 100 )
);
