package com.ms.stock.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.stock.domain.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query("SELECT s FROM Stock s WHERE s.productId = :productId")
	Optional<Stock> findByProductId(@Param("productId") Long productId);
}
