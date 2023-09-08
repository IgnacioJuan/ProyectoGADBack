package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionEvidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AprobacionEvidenciaRepository extends JpaRepository<AprobacionEvidencia, Long> {
    @Query(value = "SELECT * from aprobacion_evidencia where visible = true", nativeQuery = true)
    List<AprobacionEvidencia> listarAprobacionEvidencia();

    @Query(value = "SELECT ae.observacion,ae.estado, ae.id_evidencia,  p.primer_nombre, p.primer_apellido, ae.fecha_aprobacion\n" +
            "FROM aprobacion_evidencia ae\n" +
            "INNER JOIN usuarios u ON ae.id_usuario = u.id\n" +
            "INNER JOIN persona p ON u.persona_id_persona = p.id_persona\n" +
            "WHERE ae.visible = true\n" +
            "  AND u.visible = true\n" +
            "  AND p.visible = true\n" +
            "  AND ae.id_evidencia = :archivoId \n ", nativeQuery = true)
    List<Object[]> listarAprobacionEvidenciaPorIdArchivo(@Param("archivoId") Long archivoId);










}
