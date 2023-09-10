package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Aprobacion_SolicitudPresupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprobacionSolicitudPresupuesto_Repository extends JpaRepository<Aprobacion_SolicitudPresupuesto, Long> {
    @Query(value = "SELECT * from aprobacion_solicitud_presupuesto where visible = true", nativeQuery = true)
    List<Aprobacion_SolicitudPresupuesto> listarAprobacionSolicitud();












}
