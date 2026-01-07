package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<ReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		
		LocalDate endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if(!maxDate.isBlank()){
			endDate = LocalDate.parse(maxDate);
		}

		LocalDate startDate = endDate.minusYears(1L);
		if(!minDate.isBlank()){
			startDate = LocalDate.parse(minDate);
		}
		
        Page<ReportProjection> result = repository.getReport(startDate, endDate, name, pageable);
		return result.map(x -> new ReportDTO(x));
    }

    public List<SummaryDTO> getSummary(String minDate, String maxDate) {

		LocalDate endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if(!maxDate.isBlank()){
			endDate = LocalDate.parse(maxDate);
		}

		LocalDate startDate = endDate.minusYears(1L);
		if(!minDate.isBlank()){
			startDate = LocalDate.parse(minDate);
		}

        List<SummaryProjection> result = repository.getSummary(startDate, endDate);
		return result.stream().map(x -> new SummaryDTO(x)).collect(Collectors.toList());
    }
}
