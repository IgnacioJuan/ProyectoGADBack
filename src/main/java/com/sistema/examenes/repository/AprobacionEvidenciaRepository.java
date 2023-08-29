package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionEvidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprobacionEvidenciaRepository extends JpaRepository<AprobacionEvidencia, Long>{

    @Query(value = "SELECT * from aprobacion_evidencia where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<AprobacionEvidencia> listarAprobacionEvidencia();
}
