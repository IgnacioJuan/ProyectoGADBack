package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.entity.ReformaTraspaso_I;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PresupuestoExternoRepository extends JpaRepository<PresupuestoExterno, Long> {

    @Query(value = "SELECT * from presupuesto_externo where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<PresupuestoExterno> listarPresupuestoExterno();

    @Query(value = "SELECT p.id_presupuesto_externo, p.nombre_institucion, p.valor, p.fecha, p.observacion, a.nombre AS nombre_actividad, pr.nombre AS nombre_proyecto " +
            "FROM presupuesto_externo p " +
            "JOIN actividades a ON p.id_actividad = a.id_actividad " +
            "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
            "JOIN aprobacion_poa ap ON aa.id_poa = ap.id_aprobacionpoa " +
            "JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto", nativeQuery = true)
    List<Object[]> listarPEActividades();
}
