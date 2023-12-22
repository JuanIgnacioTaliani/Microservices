CREATE TABLE orders (
	id INT NOT NULL AUTO_INCREMENT,
    order_number VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_item (
	id INT NOT NULL AUTO_INCREMENT,
    sku VARCHAR(10) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);
