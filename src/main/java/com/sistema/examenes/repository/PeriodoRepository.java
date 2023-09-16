package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
        @Query(value = "SELECT * from periodo where visible =true", nativeQuery = true)
        List<Periodo> listarPeriodo();

        @Query(value = " SELECT " +
                        "    (SUM(pe.porcentaje)*100)/(100*count(pe.referencia)) AS porcentaje_total, " +
                        "    pe.referencia " +
                        "FROM " +
                        "    periodo pe " +
                        "JOIN " +
                        "    actividades act ON pe.id_actividad = act.id_actividad " +
                        "JOIN " +
                        "    aprobacion_actividad aa ON act.id_actividad = aa.id_actividad " +
                        "JOIN " +
                        "    poa p ON aa.id_poa = p.id_poa " +
                        "WHERE " +
                        "    p.id_poa = :idPoa AND " +
                        "    pe.visible = true " +
                        "GROUP BY " +
                        "    pe.referencia " +
                        "ORDER BY " +
                        "    pe.referencia ", nativeQuery = true)
        List<Object[]> obtenerPorcentajeYReferenciaPorPoa(@Param("idPoa") Long idPoa);

        @Query(value = "SELECT " +
                        "SUM(a.presupuesto_referencial) AS total_presupuesto_referencial, " +
                        "SUM(a.recursos_propios) AS total_recursos_propios, " +
                        "COALESCE(SUM(pe.valor), 0) AS total_presupuesto_externo " +
                        "FROM actividades a " +
                        "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
                        "JOIN poa p ON aa.id_poa = p.id_poa " +
                        "LEFT JOIN presupuesto_externo pe ON a.id_actividad = pe.id_actividad " +
                        "WHERE p.id_poa = :idPoa " +
                        "GROUP BY p.id_poa", nativeQuery = true)
        List<Object[]> obtenerTotalesPorPoa(@Param("idPoa") Long idPoa);

}
