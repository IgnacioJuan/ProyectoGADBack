package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.entity.ReformaTraspaso_I;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresupuestoExternoRepository extends JpaRepository<PresupuestoExterno, Long> {

    @Query(value = "SELECT * from presupuesto_externo where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<PresupuestoExterno> listarPresupuestoExterno();

    @Query(value = "SELECT p.id_presupuesto_externo, p.nombre_institucion, p.valor, p.fecha, p.observacion " +
            "FROM presupuesto_externo p " +
            "WHERE p.id_actividad = :actividadId AND p.visible = true " +
            "ORDER BY p.fecha DESC", nativeQuery = true)
    List<Object[]> listarPEActividades(@Param("actividadId") Long actividadId);

}
