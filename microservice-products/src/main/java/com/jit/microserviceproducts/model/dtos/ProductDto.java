package com.jit.microserviceproducts.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    private String sku;

    private String name;

    private String description;

    private Double price;

    private Boolean status;
}
