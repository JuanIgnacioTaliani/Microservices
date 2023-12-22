package com.jit.microserviceorders.services.mapper;

import com.jit.microserviceorders.model.dtos.OrderDto;
import com.jit.microserviceorders.model.dtos.OrderItemDto;
import com.jit.microserviceorders.model.entities.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DtoMapper implements Function<Order, OrderDto> {
    @Override
    public OrderDto apply(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderItems(order.getOrderItems().stream().map(
                        item -> OrderItemDto.builder()
                                .id(item.getId())
                                .sku(item.getSku())
                                .price(item.getPrice())
                                .quantity(item.getQuantity())
                                .build())
                        .toList())
                .build();
    }
}
