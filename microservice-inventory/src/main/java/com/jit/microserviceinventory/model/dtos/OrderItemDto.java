package com.jit.microserviceinventory.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;

    private String sku;

    private Double price;

    private Long quantity;

    private Long orderId;
}
