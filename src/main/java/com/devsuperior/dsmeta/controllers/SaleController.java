package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;


@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(name = "minDate", defaultValue = "") String minDateStr,
													  @RequestParam(name = "maxDate", defaultValue = "") String maxDateStr,
													  @RequestParam(name = "name", defaultValue = "") String name,
													  Pageable pageable) {
		Page<SaleMinDTO> result = service.getReport(minDateStr, maxDateStr, name, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleProjection>> getSummary(@RequestParam(name = "minDate", defaultValue = "") String minDateStr,
													   @RequestParam(name = "maxDate", defaultValue = "") String maxDateStr) {
		List<SaleProjection> result = service.getSummary(minDateStr, maxDateStr);
		return ResponseEntity.ok((result));
	}
}