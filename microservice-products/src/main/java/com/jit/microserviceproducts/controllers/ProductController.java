package com.jit.microserviceproducts.controllers;

import com.jit.microserviceproducts.model.dtos.ProductDto;
import com.jit.microserviceproducts.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void add(@RequestBody ProductDto productDto) {
        this.productService.add(productDto);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody ProductDto productDto, @PathVariable Long id) {
        this.productService.update(productDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }

    @GetMapping(value = "/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductDto getById(@PathVariable Long id) {
        return this.productService.getById(id);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ProductDto> getAll() {
        return this.productService.getAll();
    }
}
