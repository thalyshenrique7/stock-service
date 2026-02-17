package com.ms.stock.service;

import com.ms.stock.infrastructure.messaging.event.OrderCreatedEvent;

public interface StockMovementService {

	void registerMovement(OrderCreatedEvent orderCreatedEvent);
}
