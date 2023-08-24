package com.sistema.examenes.repository;

import com.sistema.examenes.entity.ReporteActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReporteActividadRepository extends JpaRepository<ReporteActividad, Long> {

    @Query(value = "SELECT * from reporte_actividad where visible = true ORDER BY fecha ASC", nativeQuery = true)
    List<ReporteActividad> listarReporteActividades();
}
