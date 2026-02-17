package com.ms.stock.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class StockResponseDTO {

	private Long productId;

	private BigDecimal availableQuantity;

	private String message;

	private Calendar createdAt;

	public StockResponseDTO(Long productId, BigDecimal availableQuantity, String message, Calendar createdAt) {

		this.productId = productId;
		this.availableQuantity = availableQuantity;
		this.message = message;
		this.createdAt = createdAt;
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

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Calendar getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {

		this.createdAt = createdAt;
	}

}
