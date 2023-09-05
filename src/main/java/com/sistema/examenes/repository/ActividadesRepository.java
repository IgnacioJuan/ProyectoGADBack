package com.sistema.examenes.repository;

import com.sistema.examenes.dto.DetalleActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadDTO;
import com.sistema.examenes.entity.Actividades;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadesRepository extends JpaRepository<Actividades, Long> {

        @Query(value = "SELECT * from actividades where visible = true ORDER BY nombre ASC", nativeQuery = true)
        List<Actividades> listarActividades();

        @Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado "
                        +
                        "FROM actividades a " +
                        "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
                        "JOIN poa p ON aa.id_poa = p.id_poa " +
                        "WHERE a.visible = true AND p.id_poa = :poaId " +
                        "ORDER BY a.nombre ASC", nativeQuery = true)
        List<Object[]> listarActividadesPorIdPoa(@Param("poaId") Long poaId);

        /*
         * @Query(value =
         * "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado, "
         * +
         * "CONCAT(pe.primer_nombre, ' ', pe.primer_apellido) AS responsable " +
         * "FROM actividades a " +
         * "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
         * "JOIN poa p ON aa.id_poa = p.id_poa " +
         * "JOIN usuarios u ON a.id_responsable = u.id " +
         * "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
         * "WHERE a.visible = true AND p.id_poa = :poaId " +
         * "ORDER BY a.nombre ASC", nativeQuery = true)
         * List<Object[]> listarActividadesPorIdPoa(@Param("poaId") Long poaId);
         */
        @Query(value = "select * from actividades where id_responsable = :id_resp and visible =true;", nativeQuery = true)
        List<Actividades> listarActividadeSPORresponsable(Long id_resp);

        @Query(value = "SELECT " +
                        "    u.id,"
                        +
                        "    p.primer_nombre || ' ' || p.segundo_nombre || ' ' || p.primer_apellido || ' ' || p.segundo_apellido AS nombre_responsable,"
                        +
                        "    p.cargo AS cargo_responsable," +
                        "    COUNT(a.id_actividad) AS numero_de_actividades " +
                        "FROM " +
                        "    usuarios u " +
                        "JOIN " +
                        "    persona p ON u.persona_id_persona = p.id_persona " +
                        "JOIN " +
                        "    actividades a ON u.id = a.id_responsable " +
                        "WHERE " +
                        "    a.visible = true AND u.visible = true AND p.visible = true " +
                        "GROUP BY " +
                        "    u.id, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.cargo " +
                        "HAVING " +
                        "    COUNT(a.id_actividad) > 0 " +
                        "ORDER BY " +
                        "    nombre_responsable", nativeQuery = true)
        List<Object[]> obtenerUsuariosConActividades();

        @Query(value = "SELECT " +
                        "    a.id_actividad," +
                        "    a.codificado," +
                        "    a.descripcion," +
                        "    a.devengado," +
                        "    a.estado," +
                        "    a.nombre AS nombre_actividad," +
                        "    a.presupuesto_referencial," +
                        "    a.recursos_propios," +
                        "    p.primer_nombre || ' ' || p.primer_apellido AS nombre_responsable " +
                        "FROM " +
                        "    usuarios u " +
                        "JOIN " +
                        "    actividades a ON u.id = a.id_responsable " +
                        "JOIN " +
                        "    persona p ON u.persona_id_persona = p.id_persona " +
                        "WHERE " +
                        "    a.visible = true AND u.visible = true " +
                        "    AND u.id = :idUsuario " +
                        "ORDER BY " +
                        "    a.id_actividad", nativeQuery = true)
        List<Object[]> obtenerDetalleActividades(@Param("idUsuario") Long idUsuario);

        // Para llenar las actividades del poa
        @Query(value = "SELECT " +
                        "    a.id_actividad," +
                        "    a.nombre AS nombre_actividad," +
                        "    a.descripcion," +
                        "    a.presupuesto_referencial," +
                        "    a.codificado," +
                        "    a.devengado," +
                        "    a.recursos_propios," +
                        "    a.estado," +
                        "    per.primer_nombre || ' ' || per.primer_apellido AS responsable " +
                        "FROM " +
                        "    usuarios u " +
                        "JOIN " +
                        "    actividades a ON u.id = a.id_responsable " +
                        "JOIN " +
                        "    aprobacion_actividad apac ON a.id_actividad = apac.id_actividad " +
                        "JOIN " +
                        "    poa p ON apac.id_poa = p.id_poa " +
                        "JOIN " +
                        "    persona per ON u.persona_id_persona = per.id_persona " +
                        "WHERE " +
                        "    a.visible = true AND u.visible = true " +
                        "    AND p.id_poa = :id_Poa " +
                        "    AND a.estado = 'Aprobado' " +
                        "ORDER BY " +
                        "    a.id_actividad", nativeQuery = true)
        List<Object[]> obtenerDetalleActividadesAprob(@Param("id_Poa") Long id_Poa);

        // query - actividades que tenga archivos rechazados
        @Query(value = "SELECT DISTINCT a.*\n" +
                        "FROM actividades a\n" +
                        "INNER JOIN archivo ar ON a.id_actividad = ar.id_actividad\n" +
                        "WHERE LOWER(ar.estado) = 'rechazado';\n", nativeQuery = true)
        List<Actividades> listarActEviRechazados();

        @Query(value = "SELECT u.id, u.username, pe.primer_nombre, pe.primer_apellido, pe.cargo, a.nombre " +
                        "FROM actividades a " +
                        "JOIN usuarios u ON a.id_responsable = u.id " +
                        "JOIN persona pe ON u.persona_id_persona = pe.id_persona", nativeQuery = true)
        List<Object[]> listarUsuariosAsignadosAActividades();

        /*
         * @Modifying
         * 
         * @Transactional
         * 
         * @Query(value =
         * "UPDATE actividades SET codificado = codificado + :valor WHERE id_actividad = :idActividad"
         * , nativeQuery = true)
         * void actualizarCodificado(@Param("idActividad") Long
         * idActividad, @Param("valor") double valor);
         */

}