package com.ms.stock.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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

	@Value("${broker.exchange.stock}")
	private String stockExchange;

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

	@Bean(name = "stockListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory stockListenerContainerFactory(ConnectionFactory connectionFactory,
			Jackson2JsonMessageConverter messageConverter) {

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);

		factory.setAdviceChain(
				RetryInterceptorBuilder.stateless().maxAttempts(5).backOffOptions(1000, 2.0, 10000).build());

		return factory;
	}

	@Bean
	public TopicExchange stockExchange() {

		return new TopicExchange(stockExchange, true, false);
	}

}
