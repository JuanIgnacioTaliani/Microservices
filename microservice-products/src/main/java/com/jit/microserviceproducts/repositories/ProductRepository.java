package com.jit.microserviceproducts.repositories;

import com.jit.microserviceproducts.model.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
