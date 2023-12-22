package com.jit.microserviceproducts.services.mappers;

import com.jit.microserviceproducts.model.dtos.ProductDto;
import com.jit.microserviceproducts.model.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DtoMapper implements Function<Product, ProductDto> {
    @Override
    public ProductDto apply(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
