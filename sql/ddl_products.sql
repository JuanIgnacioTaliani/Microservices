CREATE TABLE public.product
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    sku character varying(10) NOT NULL,
    name character varying(45) NOT NULL,
    description character varying(90) NOT NULL,
    price double precision NOT NULL,
    status boolean NOT NULL,
    PRIMARY KEY (id)
);