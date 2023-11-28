package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    @Query(value = "SELECT * from programa where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Programa> listarProgramas();

    @Query(value = "SELECT id_programa, nombre, descripcion FROM programa WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarProgramasPorNombre(@Param("nombre") String nombre);

    // Reporte de programas
    @Query(value = "SELECT prog.id_programa as idPrograma, " +
            "prog.nombre, " +
            "SUM(act.codificado) AS codificado, " +
            "SUM(act.devengado) AS devengado, " +
            "(SUM(act.devengado) / SUM(act.codificado) * 100) AS porc_ejecucion, " +
            "MAX(p.fecha_creacion) AS ultima_fecha_creacion " +
            "FROM programa prog " +
            "JOIN proyecto pr ON prog.id_programa = pr.id_programa " +
            "JOIN poa p ON pr.id_proyecto = p.id_proyecto " +
            "JOIN actividades act ON p.id_poa = act.id_poa " +
            "WHERE prog.visible = true AND p.estado = 'APROBADO' " +
            "GROUP BY prog.id_programa, prog.nombre " +
            "ORDER BY ultima_fecha_creacion ASC", nativeQuery = true)
    List<Object[]> obtenerReportIProgramas();

    // Reporte de programas --> Proyectos
    @Query(value = "SELECT pr.id_proyecto, " +
            "prog.nombre AS nombre_programa, " +
            "pr.nombre AS nombre_proyecto, " +
            "SUM(act.codificado) AS codificado, " +
            "SUM(act.devengado) AS devengado, " +
            "(SUM(act.devengado) / SUM(act.codificado) * 100) AS porc_ejecucion, " +
            "MAX(p.fecha_creacion) AS ultima_fecha_creacion " +
            "FROM programa prog " +
            "JOIN proyecto pr ON prog.id_programa = pr.id_programa " +
            "JOIN poa p ON pr.id_proyecto = p.id_proyecto " +
            "JOIN actividades act ON p.id_poa = act.id_poa " +
            "WHERE prog.id_programa = :idPrograma " +
            "GROUP BY pr.id_proyecto, prog.nombre, pr.nombre " +
            "ORDER BY ultima_fecha_creacion ASC", nativeQuery = true)
    List<Object[]> obtenerReporteProyectosPorPrograma(@Param("idPrograma") Long idPrograma);
}
