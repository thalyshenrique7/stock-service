package com.ms.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.stock.domain.model.StockMovement;
import com.ms.stock.domain.repository.StockMovementRepository;
import com.ms.stock.enums.MovementStatus;
import com.ms.stock.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.stock.infrastructure.messaging.event.OrderItemCreatedEvent;
import com.ms.stock.service.StockMovementService;

@Component
public class StockMovementServiceImpl implements StockMovementService {

	@Autowired
	private StockMovementRepository stockMovementRepository;

	@Override
	public void registerMovement(OrderCreatedEvent orderCreatedEvent) {

		for (OrderItemCreatedEvent item : orderCreatedEvent.getItems()) {

			StockMovement stockMovement = new StockMovement();
			stockMovement.setOrderId(orderCreatedEvent.getOrderId());
			stockMovement.setProductId(item.getProductId());
			stockMovement.setQuantity(item.getQuantity());
			stockMovement.setStatus(MovementStatus.CONFIRM);

			try {

				this.stockMovementRepository.save(stockMovement);

			} catch (Exception e) {
				throw new RuntimeException("Ocorreu um problema ao tentar registrar o movimento do estoque.");
			}
		}

	}

}
