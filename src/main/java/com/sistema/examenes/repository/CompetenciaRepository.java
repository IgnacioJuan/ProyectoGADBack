package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    @Query(value = "SELECT * from competencia where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Competencia> listarCompetencias();

    @Query(value = "SELECT * FROM competencia WHERE id_competencia= :id AND visible = true", nativeQuery = true)
    Competencia obtenerCompetenciaId(@Param("id") Long id);

    @Query(value = "SELECT id_competencia, nombre, descripcion FROM competencia WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarCompetenciasPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT id_competencia, nombre, descripcion FROM competencia WHERE visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<Object[]> listado();

    // Reporte de competencias
    @Query(value = "SELECT c.id_competencia, c.nombre, SUM(act.codificado) AS codificado, " +
            "SUM(act.devengado) AS devengado, " +
            "((SUM(act.devengado)/SUM(act.codificado))*100) AS porc_ejecucion, " +
            "MAX(p.fecha_creacion) AS ultima_fecha_creacion " +
            "FROM competencia c " +
            "JOIN proyecto pr ON c.id_competencia = pr.id_competencia " +
            "JOIN poa p ON pr.id_proyecto = p.id_proyecto " +
            "JOIN actividades act ON p.id_poa = act.id_poa " +
            "WHERE c.visible = true AND p.estado='APROBADO' " +
            "GROUP BY c.id_competencia, c.nombre " +
            "ORDER BY ultima_fecha_creacion ASC", nativeQuery = true)
    List<Object[]> obtenerReportICompetencias();

    // Reporte de competencias -> reporte de proyectos-POA

    @Query(value = "SELECT pr.id_proyecto, " +
            "c.nombre AS nombre_competencia, " +
            "pr.nombre AS nombre_proyecto, " +
            "SUM(act.codificado) AS codificado, " +
            "SUM(act.devengado) AS devengado, " +
            "((SUM(act.devengado)/SUM(act.codificado))*100) AS porc_ejecucion, " +
            "MAX(p.fecha_creacion) AS ultima_fecha_creacion " +
            "FROM competencia c " +
            "JOIN proyecto pr ON c.id_competencia = pr.id_competencia " +
            "JOIN poa p ON pr.id_proyecto = p.id_proyecto " +
            "JOIN actividades act ON p.id_poa = act.id_poa " +
            "WHERE c.id_competencia = :competenciaId " +
            "GROUP BY c.id_competencia, pr.id_proyecto " +
            "ORDER BY ultima_fecha_creacion ASC", nativeQuery = true)
    List<Object[]> obtenerReporteProyectosPorCompetencia(@Param("competenciaId") Long competenciaId);


    // Reporte de competencias -> reporte de proyectos-POA -> reporte actividades de los proyectos-POA
    @Query(value = "SELECT act.id_actividad, " +
            "pr.nombre AS nombre_proyecto, " +
            "act.nombre AS nombre_actividad, " +
            "SUM(act.codificado) AS codificado, " +
            "SUM(act.devengado) AS devengado, " +
            "((SUM(act.devengado)/SUM(act.codificado))*100) AS porc_ejecucion " +
            "FROM proyecto pr " +
            "JOIN poa p ON pr.id_proyecto = p.id_proyecto " +
            "JOIN actividades act ON p.id_poa = act.id_poa " +
            "WHERE pr.id_proyecto = :proyectoId " +
            "GROUP BY pr.id_proyecto, act.id_actividad", nativeQuery = true)
    List<Object[]> obtenerReporteActividadesPorProyecto(@Param("proyectoId") Long proyectoId);

}