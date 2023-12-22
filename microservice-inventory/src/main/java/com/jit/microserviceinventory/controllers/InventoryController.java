package com.jit.microserviceinventory.controllers;

import com.jit.microserviceinventory.model.dtos.BaseResponse;
import com.jit.microserviceinventory.model.dtos.OrderItemDto;
import com.jit.microserviceinventory.model.entities.Inventory;
import com.jit.microserviceinventory.services.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(value = "/get/{id}")
    public Inventory getById(@PathVariable Long id) {
        return inventoryService.getById(id);
    }

    @GetMapping(value = "get")
    public List<Inventory> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping(value = "/{sku}")
    public boolean isInStock(@PathVariable String sku) {
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    public BaseResponse areInStock(@RequestBody List<OrderItemDto> orderItems) {
        return inventoryService.areInStock(orderItems);
    }
}
