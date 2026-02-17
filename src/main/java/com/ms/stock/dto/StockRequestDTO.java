package com.ms.stock.dto;

import java.math.BigDecimal;

public class StockRequestDTO {

	private Long id;

	private Long productId;

	private BigDecimal availableQuantity;
	private BigDecimal reservedQuantity;
	private BigDecimal minimumQuantity;
	private BigDecimal maximumQuantity;

	private boolean active;

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

}
