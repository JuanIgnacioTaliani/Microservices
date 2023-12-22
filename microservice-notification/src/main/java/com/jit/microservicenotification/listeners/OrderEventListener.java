package com.jit.microservicenotification.listeners;

import com.jit.microservicenotification.events.OrderEvent;
import com.jit.microservicenotification.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {
    @KafkaListener(topics = "orders-topic")
    public void handleOrderNotifications(String message) {
        OrderEvent orderEvent = JsonUtils.fromJson(message, OrderEvent.class);

        // Send email, SMS o whatever
        // Notify to other services

        log.info("Received order event: " + message);

    }
}
