package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.SummaryProjection;

public class SummaryDTO {

	private String sellerName;
	private Double total;
	
	public SummaryDTO(String sellerName, Double total, LocalDate date) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	public SummaryDTO(SummaryProjection projection) {
		sellerName = projection.getSellerName();
		total = projection.getTotal();
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
