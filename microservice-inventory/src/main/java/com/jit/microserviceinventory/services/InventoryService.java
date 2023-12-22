package com.jit.microserviceinventory.services;

import com.jit.microserviceinventory.model.dtos.BaseResponse;
import com.jit.microserviceinventory.model.dtos.OrderItemDto;
import com.jit.microserviceinventory.model.entities.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory getById(Long id);

    boolean existsById(Long id);

    List<Inventory> getAll();

    boolean isInStock(String sku);

    BaseResponse areInStock(List<OrderItemDto> orderItems);
}
