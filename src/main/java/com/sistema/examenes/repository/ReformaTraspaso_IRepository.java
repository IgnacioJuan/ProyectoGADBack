package com.sistema.examenes.repository;

import com.sistema.examenes.entity.ReformaTraspaso_I;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReformaTraspaso_IRepository extends JpaRepository<ReformaTraspaso_I, Long>{

    @Query(value = "SELECT * from reforma_traspasoI where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<ReformaTraspaso_I> listarReformasTraspaso_I();


    @Query(value = "SELECT p.id_reftras_i, p.valor, p.fecha " +
            "FROM reforma_traspasoi p " +
            "WHERE p.id_actividad = :actividadId AND p.visible = true " +
            "ORDER BY p.fecha DESC", nativeQuery = true)
    List<Object[]> listarRTIActividades(@Param("actividadId") Long actividadId);
}
