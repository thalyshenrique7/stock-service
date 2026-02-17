package com.ms.stock.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ms.stock.enums.MovementStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_movement")
public class StockMovement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985098201651404172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "order_id")
	private Long orderId;

	@Column(nullable = false)
	private BigDecimal quantity = BigDecimal.ZERO;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MovementStatus status;

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

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public BigDecimal getQuantity() {

		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;
	}

	public MovementStatus getStatus() {

		return status;
	}

	public void setStatus(MovementStatus status) {

		this.status = status;
	}

}
