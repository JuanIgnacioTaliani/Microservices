package com.jit.microserviceorders.services.mapper;

import com.jit.microserviceorders.model.dtos.OrderDto;
import com.jit.microserviceorders.model.entities.Order;
import com.jit.microserviceorders.model.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class EntityMapper implements Function<OrderDto, Order> {
    @Override
    public Order apply(OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .orderNumber(orderDto.getOrderNumber())
                .build();

        List<OrderItem> orderItems = orderDto.getOrderItems().stream().map(
                item -> OrderItem.builder()
                        .id(item.getId())
                        .sku(item.getSku())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .order(order)
                        .build()).toList();

        order.setOrderItems(orderItems);

        return order;
    }
}
