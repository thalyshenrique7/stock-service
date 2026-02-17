package com.ms.stock.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.stock.domain.model.Stock;
import com.ms.stock.domain.repository.StockRepository;
import com.ms.stock.dto.StockRequestDTO;
import com.ms.stock.dto.StockResponseDTO;
import com.ms.stock.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.stock.infrastructure.messaging.event.OrderItemCreatedEvent;
import com.ms.stock.mapper.StockMapper;
import com.ms.stock.service.StockMovementService;
import com.ms.stock.service.StockService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	private final static String DESCRIPTION_STOCK_CREATED = "Estoque criado com sucesso.";

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private StockMovementService stockMovementService;

	@Override
	public StockResponseDTO save(StockRequestDTO stockRequest) {

		Stock stock = this.stockMapper.toEntity(stockRequest);

		stock.setCreatedAt(Calendar.getInstance());
		stock.setUpdatedAt(Calendar.getInstance());

		try {

			this.stockRepository.save(stock);

		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um problema ao tentar salvar o estoque.");
		}

		StockResponseDTO stockResponse = new StockResponseDTO(stock.getProductId(), stock.getAvailableQuantity(),
				stock.getReservedQuantity(), stock.getMinimumQuantity(), stock.getMaximumQuantity(), stock.isActive(),
				DESCRIPTION_STOCK_CREATED, stock.getCreatedAt());

		return stockResponse;
	}

	@Override
	public Stock findByProductId(Long productId) {

		Stock stock = null;
		try {

			stock = this.stockRepository.findByProductId(productId);

		} catch (Exception e) {
			throw new RuntimeException("Estoque não encontrado para o produto informado.");
		}

		return stock;
	}

	@Override
	public void reserveStock(OrderCreatedEvent orderCreatedEvent) {

		for (OrderItemCreatedEvent item : orderCreatedEvent.getItems()) {

			Stock stock = this.findByProductId(item.getProductId());

			BigDecimal quantity = item.getQuantity();
			stock.validateReserve(quantity);
			stock.updateStock(quantity);

			this.stockMovementService.registerMovement(orderCreatedEvent);
		}
	}

}
