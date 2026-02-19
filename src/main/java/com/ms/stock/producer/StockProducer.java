package com.ms.stock.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.common.infrastructure.messaging.event.StockFailedEvent;
import com.ms.common.infrastructure.messaging.event.StockReservedEvent;

@Component
public class StockProducer {

	final RabbitTemplate rabbitTemplate;

	public StockProducer(RabbitTemplate rabbitTemplate) {

		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${broker.exchange.stock}")
	private String exchange;

	@Value("${broker.routing.stock-reserved}")
	private String stockReservedRoutingKey;

	@Value("${broker.routing.stock-failed}")
	private String stockFailedRoutingKey;

	public void publishStockReserved(Long orderId) {

		StockReservedEvent stockReservedEvent = new StockReservedEvent();
		stockReservedEvent.setOrderId(orderId);

		this.rabbitTemplate.convertAndSend(exchange, stockReservedRoutingKey, stockReservedEvent);
	}

	public void publishStockFailed(Long orderId) {

		StockFailedEvent stockFailedEvent = new StockFailedEvent();
		stockFailedEvent.setOrderId(orderId);

		this.rabbitTemplate.convertAndSend(exchange, stockFailedRoutingKey, stockFailedEvent);
	}

}
