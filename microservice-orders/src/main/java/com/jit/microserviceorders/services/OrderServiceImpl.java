package com.jit.microserviceorders.services;

import com.jit.microserviceorders.config.WebClientConfig;
import com.jit.microserviceorders.events.OrderEvent;
import com.jit.microserviceorders.model.dtos.BaseResponse;
import com.jit.microserviceorders.model.dtos.OrderDto;
import com.jit.microserviceorders.model.entities.Order;
import com.jit.microserviceorders.model.enums.OrderStatus;
import com.jit.microserviceorders.repositories.OrderRepository;
import com.jit.microserviceorders.services.mapper.DtoMapper;
import com.jit.microserviceorders.services.mapper.EntityMapper;
import com.jit.microserviceorders.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final DtoMapper dtoMapper;

    private final EntityMapper entityMapper;

    private final WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, DtoMapper dtoMapper,
                            EntityMapper entityMapper, WebClient.Builder webClientBuilder,
                            KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
        this.webClientBuilder = webClientBuilder;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public OrderDto add(OrderDto orderDto) {
        // check for stock
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("lb://microservice-inventory/api/v1/inventory/in-stock")
                .bodyValue(orderDto.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if (result != null && !result.hasErrors()) {
            Order order = this.entityMapper.apply(orderDto);

            this.orderRepository.save(order);

            log.info("Product added: {}", order);

            this.kafkaTemplate.send("orders-topic", JsonUtils.toJson(
                    new OrderEvent(order.getOrderNumber(),
                            order.getOrderItems().size(),
                            OrderStatus.PLACED)
                    )
            );

            return this.dtoMapper.apply(order);
        } else {
            throw new IllegalArgumentException("Some of the products are out of stock");
        }
    }

    @Override
    public OrderDto getById(Long id) {
        Optional<Order> product = this.orderRepository.findById(id);

        return product.map(dtoMapper).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return this.orderRepository.existsById(id);
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> productList = (List<Order>) this.orderRepository.findAll();

        return productList.stream().map(this.dtoMapper).toList();
    }
}
