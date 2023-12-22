package com.jit.microserviceorders.controllers;

import com.jit.microserviceorders.model.dtos.OrderDto;
import com.jit.microserviceorders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto add(@RequestBody OrderDto orderDto) {
        return this.orderService.add(orderDto);
    }

    @GetMapping(value = "/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }
}
