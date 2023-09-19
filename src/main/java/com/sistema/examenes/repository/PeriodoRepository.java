package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.projection.presupuestPeriodoProjection;
import com.sistema.examenes.projection.totalPresupuestoGeneralProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

        @Query(value = "SELECT p FROM Periodo p WHERE p.actividad.id_actividad = :actividadId")
        List<Periodo> listarPeriodosPorActividad(@Param("actividadId") Long actividadId);

        @Modifying
        @Query("DELETE FROM Periodo p WHERE p.actividad.id_actividad = :actividadId")
        void eliminarPorActividad(@Param("actividadId") Long actividadId);

        @Query(value = "SELECT " +
                "p.id_actividad, " +
                "p.fecha_inicio, " +
                "p.fecha_fin, " +
                "a.codificado * (p.porcentaje / 100.0) AS inversion, " +
                "p.referencia, " +
                "p.porcentaje, " +
                "COALESCE(SUM(e.valor), 0) AS ejecucion " +
                "FROM " +
                "PERIODO p " +
                "LEFT JOIN " +
                "ACTIVIDADES a ON p.id_actividad = a.id_actividad " +
                "LEFT JOIN " +
                "archivo e ON a.id_actividad = e.id_actividad " +
                "AND (e.fecha BETWEEN p.fecha_inicio AND p.fecha_fin) " +
                "AND e.estado = 'APROBADO' " +
                "WHERE " +
                "a.id_actividad = :idActividad " +
                "AND p.porcentaje <> 0 " +
                "GROUP BY " +
                "p.id_actividad, p.id_periodo, a.id_actividad, p.porcentaje, p.fecha_inicio, p.fecha_fin " +
                "ORDER BY " +
                "a.id_actividad ,p.referencia ", nativeQuery = true)
        List<presupuestPeriodoProjection> presupuestoGeneral(Long idActividad);

        @Query(value = "SELECT p.referencia, MAX(p.fecha_inicio) as periodo_inicio, MAX(p.fecha_fin) as periodo_fin, " +
                "SUM(a.codificado * (p.porcentaje / 100.0)) AS inversion, " +
                "COALESCE(SUM(e.valor), 0) AS ejecucion " +
                "FROM PERIODO p " +
                "LEFT JOIN ACTIVIDADes a ON p.id_actividad = a.id_actividad " +
                "LEFT JOIN archivo e ON a.id_actividad = e.id_actividad " +
                "AND (e.fecha BETWEEN p.fecha_inicio AND p.fecha_fin) and e.estado = 'APROBADO' " +
                "WHERE a.id_poa = :poaId " +
                "GROUP BY p.referencia " +
                "ORDER BY p.referencia", nativeQuery = true)
        List<totalPresupuestoGeneralProjection> totalPresupuestoGenera(Long poaId);
}
