package com.sistema.examenes.repository;

import com.sistema.examenes.entity.ReformaSuplemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReformaSuplementoRepository extends JpaRepository<ReformaSuplemento, Long>{

    @Query(value = "SELECT * from reforma_suplemento where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<ReformaSuplemento> listarReformaSuplemento();

    @Query(value = "SELECT p.id_ref_suplemento, p.valor, p.fecha, a.nombre AS nombre_actividad, pr.nombre AS nombre_proyecto " +
            "FROM reforma_suplemento p " +
            "JOIN actividades a ON p.id_actividad = a.id_actividad " +
            "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
            "JOIN aprobacion_poa ap ON aa.id_poa = ap.id_aprobacionpoa " +
            "JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto", nativeQuery = true)
    List<Object[]> listarRSActividades();
}
