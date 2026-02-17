package com.ms.stock.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class StockResponseDTO {

	private Long productId;

	private BigDecimal availableQuantity;
	private BigDecimal reservedQuantity;
	private BigDecimal minimumQuantity;
	private BigDecimal maximumQuantity;

	private boolean active;

	private String message;

	private Calendar createdAt;

	public StockResponseDTO(Long productId, BigDecimal availableQuantity, BigDecimal reservedQuantity,
			BigDecimal minimumQuantity, BigDecimal maximumQuantity, boolean active, String message,
			Calendar createdAt) {

		this.productId = productId;
		this.availableQuantity = availableQuantity;
		this.reservedQuantity = reservedQuantity;
		this.minimumQuantity = minimumQuantity;
		this.maximumQuantity = maximumQuantity;
		this.active = active;
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

	public BigDecimal getReservedQuantity() {

		return reservedQuantity;
	}

	public void setReservedQuantity(BigDecimal reservedQuantity) {

		this.reservedQuantity = reservedQuantity;
	}

	public BigDecimal getMinimumQuantity() {

		return minimumQuantity;
	}

	public void setMinimumQuantity(BigDecimal minimumQuantity) {

		this.minimumQuantity = minimumQuantity;
	}

	public BigDecimal getMaximumQuantity() {

		return maximumQuantity;
	}

	public void setMaximumQuantity(BigDecimal maximumQuantity) {

		this.maximumQuantity = maximumQuantity;
	}

	public boolean isActive() {

		return active;
	}

	public void setActive(boolean active) {

		this.active = active;
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
