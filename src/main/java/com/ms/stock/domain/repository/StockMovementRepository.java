package com.ms.stock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.stock.domain.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

}
