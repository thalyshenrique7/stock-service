package com.ms.stock.service;

import com.ms.common.infrastructure.messaging.event.OrderCreatedEvent;

public interface StockMovementService {

	void registerMovement(OrderCreatedEvent orderCreatedEvent);
}
