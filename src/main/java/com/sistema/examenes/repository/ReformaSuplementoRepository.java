package com.sistema.examenes.repository;

import com.sistema.examenes.entity.ReformaSuplemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReformaSuplementoRepository extends JpaRepository<ReformaSuplemento, Long>{

    @Query(value = "SELECT * from reforma_suplemento where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<ReformaSuplemento> listarReformaSuplemento();

    @Query(value = "SELECT p.id_ref_suplemento, p.valor, p.fecha " +
            "FROM reforma_suplemento p " +
            "WHERE p.id_actividad = :actividadId AND p.visible = true " +
            "ORDER BY p.fecha DESC", nativeQuery = true)
    List<Object[]> listarRSActividades(@Param("actividadId") Long actividadId);
}
