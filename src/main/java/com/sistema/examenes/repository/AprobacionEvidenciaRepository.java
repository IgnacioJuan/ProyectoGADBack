package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionEvidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AprobacionEvidenciaRepository extends JpaRepository<AprobacionEvidencia, Long> {
    @Query(value = "SELECT * from aprobacion_evidencia where visible = true", nativeQuery = true)
    List<AprobacionEvidencia> listarAprobacionEvidencia();
    @Query(value = "SELECT id_aprobacionevid, estado, observacion, id_evidencia\n" +
            "FROM  aprobacion_evidencia\n" +
            "WHERE id_evidencia  = :archivoId  AND  visible=true;", nativeQuery = true)
    List<Object[]> listarAprobacionEvidenciaPorIdArchivo(@Param("archivoId") Long archivoId);
}
