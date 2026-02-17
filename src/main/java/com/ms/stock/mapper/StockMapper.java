package com.ms.stock.mapper;

import java.io.Serializable;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ms.stock.domain.model.Stock;
import com.ms.stock.dto.StockRequestDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StockMapper extends Stock implements Serializable {

	private static final long serialVersionUID = 985098201651404172L;

	public StockMapper() {

		super(Stock.class);
	}

	public abstract Stock toEntity(StockRequestDTO stockRequest);

}
