package com.ms.stock.service;

import com.ms.stock.domain.model.Stock;
import com.ms.stock.dto.StockRequestDTO;
import com.ms.stock.dto.StockResponseDTO;
import com.ms.stock.infrastructure.messaging.event.OrderCreatedEvent;

public interface StockService {

	StockResponseDTO save(StockRequestDTO stockRequest);

	Stock findByProductId(Long productId);

	void reserveStock(OrderCreatedEvent orderCreatedEvent);
}
