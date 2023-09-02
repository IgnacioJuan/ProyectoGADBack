package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividades;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadesRepository extends JpaRepository<Actividades, Long>{

    @Query(value = "SELECT * from actividades where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<Actividades> listarActividades();
    @Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado " +
            "FROM actividades a " +
            "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
            "JOIN poa p ON aa.id_poa = p.id_poa " +
            "WHERE a.visible = true AND p.id_poa = :poaId " +
            "ORDER BY a.nombre ASC", nativeQuery = true)
    List<Object[]> listarActividadesPorIdPoa(@Param("poaId") Long poaId);

    /*@Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado, " +
            "CONCAT(pe.primer_nombre, ' ', pe.primer_apellido) AS responsable " +
            "FROM actividades a " +
            "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
            "JOIN poa p ON aa.id_poa = p.id_poa " +
            "JOIN usuarios u ON a.id_responsable = u.id " +
            "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
            "WHERE a.visible = true AND p.id_poa = :poaId " +
            "ORDER BY a.nombre ASC", nativeQuery = true)
    List<Object[]> listarActividadesPorIdPoa(@Param("poaId") Long poaId);*/
    @Query(value = "select * from actividades where id_responsable = :id_resp and visible =true;", nativeQuery = true)
    List<Actividades> listarActividadeSPORresponsable(Long id_resp);

    @Query(value = "SELECT u.id, u.username, pe.primer_nombre, pe.primer_apellido, pe.cargo, a.nombre " +
            "FROM actividades a " +
            "JOIN usuarios u ON a.id_responsable = u.id " +
            "JOIN persona pe ON u.persona_id_persona = pe.id_persona", nativeQuery = true)
    List<Object[]> listarUsuariosAsignadosAActividades();

    /*@Modifying
    @Transactional
    @Query(value = "UPDATE actividades SET codificado = codificado + :valor WHERE id_actividad = :idActividad", nativeQuery = true)
    void actualizarCodificado(@Param("idActividad") Long idActividad, @Param("valor") double valor);*/

}