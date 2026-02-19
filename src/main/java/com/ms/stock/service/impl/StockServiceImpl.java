package com.ms.stock.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.common.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.common.infrastructure.messaging.event.OrderItemCreatedEvent;
import com.ms.stock.domain.model.Stock;
import com.ms.stock.domain.repository.StockRepository;
import com.ms.stock.dto.StockRequestDTO;
import com.ms.stock.dto.StockResponseDTO;
import com.ms.stock.infrastructure.messaging.producer.StockProducer;
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

	@Autowired
	private StockProducer stockProducer;

	@Override
	public StockResponseDTO save(StockRequestDTO stockRequest) {

		Stock stock = this.stockMapper.toEntity(stockRequest);

		try {

			this.stockRepository.save(stock);

		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um problema ao tentar salvar o estoque.");
		}

		StockResponseDTO stockResponse = new StockResponseDTO(stock.getProductId(), stock.getAvailableQuantity(),
				DESCRIPTION_STOCK_CREATED, Calendar.getInstance());

		return stockResponse;
	}

	@Override
	public void updateStock(OrderCreatedEvent orderCreatedEvent) {

		Long orderId = orderCreatedEvent.getOrderId();

		for (OrderItemCreatedEvent item : orderCreatedEvent.getItems()) {

			Stock stock = this.stockRepository.findByProductId(item.getProductId()).orElseThrow(null);

			if (stock == null)
				stockProducer.publishStockFailed(orderId);

			stock.validateAndUpdateStock(item.getQuantity());
			stockRepository.save(stock);

			this.stockMovementService.registerMovement(orderCreatedEvent);
		}

		stockProducer.publishStockReserved(orderId);
	}

}
