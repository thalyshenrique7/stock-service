package com.ms.stock.infrastructure.messaging.event;

import java.math.BigDecimal;

import com.ms.stock.utils.BigDecimalUtils;

public class OrderItemCreatedEvent {

	private BigDecimal quantity;

	private Long productId;

	public BigDecimal getQuantity() {

		return BigDecimalUtils.zeroIfNull(quantity);
	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;
	}

	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

}
