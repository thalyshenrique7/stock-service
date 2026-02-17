package com.ms.stock.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	@Value("${broker.exchange.order}")
	private String orderExchange;

	@Value("${broker.queue.stock}")
	private String stockQueue;

	@Value("${broker.routing.order-created}")
	private String routingKey;

	@Bean
	public Queue queue() {

		return new Queue(stockQueue, true);
	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {

		ObjectMapper objectMapper = new ObjectMapper();
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean
	public TopicExchange orderExchange() {

		return new TopicExchange(orderExchange, true, false);
	}

	@Bean
	public Binding stockBinding(Queue stockQueue, TopicExchange orderExchange) {

		return BindingBuilder.bind(stockQueue).to(orderExchange).with(routingKey);
	}

}
