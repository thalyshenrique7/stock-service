package com.ms.stock.infrastructure.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.stock.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.stock.service.StockService;

@Component
public class StockConsumer {

	@Autowired
	private StockService stockService;

	@RabbitListener(queues = "${broker.queue.stock.name}")
	public void listenStockQueue(@Payload OrderCreatedEvent orderCreatedEvent) {

		this.stockService.reserveStock(orderCreatedEvent);
	}
}
