package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.ReformaTraspaso_D;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReformaTraspaso_DRepository extends JpaRepository<ReformaTraspaso_D, Long>{

    @Query(value = "SELECT * from reforma_traspasoD where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<ReformaTraspaso_D> listarReformasTraspaso_D();

    @Query(value = "SELECT p.id_reftras_d, p.valor, p.fecha " +
            "FROM reforma_traspasod p " +
            "WHERE p.id_actividad = :actividadId AND p.visible = true " +
            "ORDER BY p.fecha DESC", nativeQuery = true)
    List<Object[]> listarRTDActividades(@Param("actividadId") Long actividadId);
}
