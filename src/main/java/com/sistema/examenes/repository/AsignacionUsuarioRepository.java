package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AsignacionesUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionUsuarioRepository extends JpaRepository<AsignacionesUsuarios, Long>{

    @Query(value = "SELECT * from asignaciones_usuarios where visible = true ORDER BY fecha_asignacion DESC", nativeQuery = true)
    List<AsignacionesUsuarios> listarAsignacionesActividades();

    @Query(value = "SELECT u.id, u.username, pe.primer_nombre, pe.primer_apellido, pe.cargo, au.fecha_asignacion " +
            "FROM asignaciones_usuarios au " +
            "JOIN usuarios u ON au.id_usu_asignado = u.id " +
            "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
            "JOIN actividades a ON au.id_actividad = a.id_actividad " +
            "WHERE a.id_actividad = :actividadId AND au.visible = true " +
            "ORDER BY au.fecha_asignacion DESC", nativeQuery = true)
    List<Object[]> listarAsignacionesUsuarios(@Param("actividadId") Long actividadId);

}
