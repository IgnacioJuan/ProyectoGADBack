package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprobacionActividadRepository extends JpaRepository<AprobacionActividad, Long>{

    @Query(value = "SELECT * from aprobacion_actividad where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<AprobacionActividad> listarAprobacionActividad();
}
