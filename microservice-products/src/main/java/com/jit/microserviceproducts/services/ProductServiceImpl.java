package com.jit.microserviceproducts.services;

import com.jit.microserviceproducts.model.dtos.ProductDto;
import com.jit.microserviceproducts.model.entities.Product;
import com.jit.microserviceproducts.repositories.ProductRepository;
import com.jit.microserviceproducts.services.mappers.DtoMapper;
import com.jit.microserviceproducts.services.mappers.EntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final DtoMapper dtoMapper;

    private final EntityMapper entityMapper;

    public ProductServiceImpl(ProductRepository productRepository, DtoMapper dtoMapper, EntityMapper entityMapper) {
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public void add(ProductDto productDto) {
        Product product = this.entityMapper.apply(productDto);

        this.productRepository.save(product);

        log.info("Product added: {}", product);
    }

    @Override
    public void update(ProductDto productDto) {
        Product product = this.entityMapper.apply(productDto);

        this.productRepository.save(product);

        log.info("Product modified: {}", product);
    }

    @Override
    public void delete(Long id) {
        if (this.existsById(id)) {
            Product product = this.productRepository.findById(id).orElse(null);

            this.productRepository.deleteById(id);

            log.info("Product modified: {}", product);
        }
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);

        return product.map(dtoMapper).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return this.productRepository.existsById(id);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = (List<Product>) this.productRepository.findAll();

        return productList.stream().map(this.dtoMapper).toList();
    }
}
