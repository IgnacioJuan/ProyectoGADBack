package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.entity.ReportePeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportePeriodoRepository extends JpaRepository<ReportePeriodo, Long> {
    @Query(value = "SELECT * from reporte_periodo where visible =true", nativeQuery = true)
    List<ReportePeriodo> listarReportePeriodo();

}