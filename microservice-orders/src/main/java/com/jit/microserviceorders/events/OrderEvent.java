package com.jit.microserviceorders.events;

import com.jit.microserviceorders.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemsCount, OrderStatus orderStatus) {
}
