package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionActividad;
import com.sistema.examenes.projection.AprobacionporActividadProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprobacionActividadRepository extends JpaRepository<AprobacionActividad, Long>{

    @Query(value = "SELECT * from aprobacion_actividad where visible = true", nativeQuery = true)
    List<AprobacionActividad> listarAprobacionActividad();

    //Consulta para obtener las aprobaciones segun el id de la actividad
    @Query(value = "SELECT  (c.primer_nombre || ' ' || c.primer_apellido) AS evaluador, " +
            "a.estado, a.observacion, a.fecha_aprobacion " +
            "from aprobacion_actividad a " +
            "join usuarios b " +
            "on a.id_usuario = b.id " +
            "join persona c " +
            "on b.persona_id_persona = c.id_persona " +
            "where a.visible = true " +
            "and a.id_actividad = :id_actividad", nativeQuery = true)
    List<AprobacionporActividadProjection> listarAprobacionesporActividad(Long id_actividad);
}
