package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.ReportProjection;

public class ReportDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName;
	
	public ReportDTO(Long id, Double amount, LocalDate date, String sellerName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName;
	}
	
	public ReportDTO(ReportProjection projection) {
		id = projection.getId();
		amount = projection.getAmount();
		date = projection.getDate();
		sellerName = projection.getSellerName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}


}
