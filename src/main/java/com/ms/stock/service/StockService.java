package com.ms.stock.service;

import com.ms.common.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.stock.dto.StockRequestDTO;
import com.ms.stock.dto.StockResponseDTO;

public interface StockService {

	StockResponseDTO save(StockRequestDTO stockRequest);

	void updateStock(OrderCreatedEvent orderCreatedEvent);
}
