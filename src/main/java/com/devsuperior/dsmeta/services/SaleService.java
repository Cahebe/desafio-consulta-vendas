package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	private LocalDate result = today.minusYears(1L);


	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		LocalDate minDate = minDateStr.isEmpty() ? result : LocalDate.parse(minDateStr, fmt);
		LocalDate maxDate = maxDateStr.isEmpty() ? today : LocalDate.parse(maxDateStr, fmt);
		String cleanedSellerName = name.isEmpty() ? "" : name;

		Page<SaleMinDTO> saleMinDTOs = repository.getReport(minDate, maxDate, cleanedSellerName, pageable);
		return saleMinDTOs;
	}

	public List<SaleProjection> getSummary(String minDateStr, String maxDateStr) {
		LocalDate minDate = minDateStr.isEmpty() ? result : LocalDate.parse(minDateStr, fmt);
		LocalDate maxDate = maxDateStr.isEmpty() ? today : LocalDate.parse(maxDateStr, fmt);

		List<SaleProjection> saleMinDTOS = repository.getSummary(minDate, maxDate);
		return saleMinDTOS;
	}
}