package com.ms.stock.dto;

import java.math.BigDecimal;

public class StockRequestDTO {

	private Long id;

	private Long productId;

	private BigDecimal availableQuantity;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

	public BigDecimal getAvailableQuantity() {

		return availableQuantity;
	}

	public void setAvailableQuantity(BigDecimal availableQuantity) {

		this.availableQuantity = availableQuantity;
	}

}
