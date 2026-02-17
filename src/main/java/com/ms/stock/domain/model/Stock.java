package com.ms.stock.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.ms.stock.utils.BigDecimalUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985098201651404172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id", nullable = false, unique = true)
	private Long productId;

	@Column(name = "available_quantity")
	private BigDecimal availableQuantity = BigDecimal.ZERO;

	@Column(name = "reserved_quantity")
	private BigDecimal reservedQuantity = BigDecimal.ZERO;

	@Column(name = "minimum_quantity")
	private BigDecimal minimumQuantity = BigDecimal.ZERO;

	@Column(name = "maximum_quantity")
	private BigDecimal maximumQuantity = BigDecimal.ZERO;

	private boolean active = true;

	@Column(name = "created_at")
	private Calendar createdAt;

	@Column(name = "updated_at")
	private Calendar updatedAt;

	public Stock(Class<Stock> classStock) {

	}

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

		return BigDecimalUtils.zeroIfNull(availableQuantity);
	}

	public void setAvailableQuantity(BigDecimal availableQuantity) {

		this.availableQuantity = availableQuantity;
	}

	public BigDecimal getReservedQuantity() {

		return BigDecimalUtils.zeroIfNull(reservedQuantity);
	}

	public void setReservedQuantity(BigDecimal reservedQuantity) {

		this.reservedQuantity = reservedQuantity;
	}

	public BigDecimal getMinimumQuantity() {

		return BigDecimalUtils.zeroIfNull(minimumQuantity);
	}

	public void setMinimumQuantity(BigDecimal minimumQuantity) {

		this.minimumQuantity = minimumQuantity;
	}

	public BigDecimal getMaximumQuantity() {

		return BigDecimalUtils.zeroIfNull(maximumQuantity);
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

	public Calendar getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {

		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {

		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {

		this.updatedAt = updatedAt;
	}

	public void validateReserve(BigDecimal quantity) {

		if (BigDecimalUtils.isLessOrEqualThanZero(quantity))
			throw new RuntimeException("A quantidade para reserva deve ser maior que zero.");

		if (BigDecimalUtils.isGreaterThan(quantity, getAvailableQuantity()))
			throw new RuntimeException("Estoque insuficiente para realizar a reserva.");

		if (BigDecimalUtils.isLessThan(quantity, getMinimumQuantity()))
			throw new RuntimeException("Estoque mínimo não pode ser menor que " + getMinimumQuantity());
	}

	public void updateStock(BigDecimal quantity) {

		BigDecimal newValueStock = getAvailableQuantity().subtract(quantity);
		setAvailableQuantity(newValueStock);
	}

}
