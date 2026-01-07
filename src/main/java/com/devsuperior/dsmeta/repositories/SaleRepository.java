package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SummaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT sa.id as id, sa.date as date, sa.amount as amount, se.name as sellerName
            FROM Sale sa
            JOIN Seller se ON sa.seller.id = se.id
            AND DATE BETWEEN :minDate AND :maxDate
            AND UPPER(se.name) LIKE CONCAT('%', UPPER(:name), '%')
            """)
    Page<ReportProjection> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query("""
            SELECT se.name as sellerName, SUM(sa.amount) as total
            FROM Sale sa
            JOIN Seller se ON sa.seller.id = se.id
            AND DATE BETWEEN :minDate AND :maxDate
            GROUP BY se.id
            """)
    List<SummaryProjection> getSummary(LocalDate minDate, LocalDate maxDate);

}
