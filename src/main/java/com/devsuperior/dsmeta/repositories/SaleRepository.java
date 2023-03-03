package com.devsuperior.dsmeta.repositories;


import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;


public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true, value = "SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.name LIKE :name ")
    Page<SaleProjection> report(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
