package com.ms.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.stock.dto.StockRequestDTO;
import com.ms.stock.dto.StockResponseDTO;
import com.ms.stock.service.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "api/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping
	public ResponseEntity<StockResponseDTO> create(@Valid @RequestBody StockRequestDTO stockRequest) {

		StockResponseDTO stockResponse = this.stockService.save(stockRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(stockResponse);
	}
}
