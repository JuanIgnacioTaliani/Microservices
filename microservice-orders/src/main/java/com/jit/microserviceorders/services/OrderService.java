package com.jit.microserviceorders.services;

import com.jit.microserviceorders.model.dtos.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto add(OrderDto orderDto);

    OrderDto getById(Long id);

    boolean existsById(Long id);

    List<OrderDto> getAll();
}
