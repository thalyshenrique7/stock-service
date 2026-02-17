package com.ms.stock.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

	public Stock() {

	}

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

	public void validateReserve(BigDecimal quantity) {

		if (BigDecimalUtils.isLessOrEqualThanZero(quantity))
			throw new RuntimeException("A quantidade para reserva deve ser maior que zero.");

		if (BigDecimalUtils.isGreaterThan(quantity, getAvailableQuantity()))
			throw new RuntimeException("Estoque insuficiente para realizar a reserva.");

	}

	public void updateStock(BigDecimal quantity) {

		BigDecimal newValueStock = getAvailableQuantity().subtract(quantity);
		setAvailableQuantity(newValueStock);
	}

}
