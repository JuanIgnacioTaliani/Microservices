package com.jit.microservicenotification.events;

import com.jit.microservicenotification.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemsCount, OrderStatus orderStatus) {
}
