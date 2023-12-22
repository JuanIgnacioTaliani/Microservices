CREATE TABLE public.inventory
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    sku character varying(10) NOT NULL,
    quantity bigint NOT NULL,
    PRIMARY KEY (id)
);