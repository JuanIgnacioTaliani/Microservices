package com.jit.microserviceinventory.services;

import com.jit.microserviceinventory.model.dtos.BaseResponse;
import com.jit.microserviceinventory.model.dtos.OrderItemDto;
import com.jit.microserviceinventory.model.entities.Inventory;
import com.jit.microserviceinventory.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory getById(Long id) {
        return this.inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return this.inventoryRepository.existsById(id);
    }

    @Override
    public List<Inventory> getAll() {
        return (List<Inventory>) this.inventoryRepository.findAll();
    }

    @Override
    public boolean isInStock(String sku) {
        Optional<Inventory> inventory = this.inventoryRepository.findBySku(sku);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    @Override
    public BaseResponse areInStock(List<OrderItemDto> orderItems) {
        List<String> errorList = new ArrayList<>();

        List<String> skus = orderItems.stream().map(OrderItemDto::getSku).toList();

        List<Inventory> inventories = this.inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            Optional<Inventory> inventory = inventories.stream()
                    .filter(value -> value.getSku().equals(orderItem.getSku()))
                    .findFirst();
            if (inventory.isEmpty()) {
                errorList.add("Product with sku " + orderItem.getSku() + " doesn't exists");
            } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("Product with sku " + orderItem.getSku() + " has insufficient stock");
            }
        });

        return errorList.size() > 0
                ? new BaseResponse(errorList.toArray(new String[0]))
                : new BaseResponse(null);
    }
}
