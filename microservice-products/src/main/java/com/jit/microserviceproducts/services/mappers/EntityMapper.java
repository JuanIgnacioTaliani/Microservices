package com.jit.microserviceproducts.services.mappers;

import com.jit.microserviceproducts.model.dtos.ProductDto;
import com.jit.microserviceproducts.model.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EntityMapper implements Function<ProductDto, Product> {
    @Override
    public Product apply(ProductDto productDto) {
        return Product.builder()
                .sku(productDto.getSku())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .status(productDto.getStatus())
                .build();
    }
}
