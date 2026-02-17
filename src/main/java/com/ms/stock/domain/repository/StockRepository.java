package com.ms.stock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.stock.domain.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	Stock findByProductId(Long productId);
}
