package com.sistema.examenes.repository;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.projection.ActividadesPendientesPorPoaProjection;
import com.sistema.examenes.projection.activ_fecha_lim_projection;
import com.sistema.examenes.projection.actividad_archi_projection;
import com.sistema.examenes.projection.valorprojec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadesRepository extends JpaRepository<Actividades, Long> {

        @Query(value = "SELECT * from actividades where visible = true ORDER BY nombre ASC", nativeQuery = true)
        List<Actividades> listarActividades();

        @Query(value = "SELECT id_actividad, nombre, descripcion, presupuesto_referencial, recursos_propios, codificado, devengado, estado, id_poa from actividades where visible = true ORDER BY nombre ASC", nativeQuery = true)
        List<Object[]> listarActividades2();


        @Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado FROM actividades a JOIN poa p ON a.id_poa = p.id_poa WHERE a.visible = true AND p.id_poa = :poaId ORDER BY a.nombre ASC", nativeQuery = true)
        List<Object[]> listarActividadesPorIdPoa(@Param("poaId") Long poaId);

        //Para chris
        @Query(value = "SELECT * FROM actividades a JOIN poa p ON a.id_poa = p.id_poa WHERE a.visible = true AND p.id_poa = :poaId ORDER BY a.nombre ASC", nativeQuery = true)
        List<Actividades> listarActividadesPorIdPoa2(@Param("poaId") Long poaId);

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
        @Query(value = "SELECT * FROM public.actividades WHERE estado = 'APROBADO' AND visible = true AND id_responsable = :id_resp", nativeQuery = true)
        List<Actividades> listarActividadeSPORresponsable(Long id_resp);

        @Query(value = "SELECT " +
                        "    u.id," +
                        "    COALESCE(p.primer_nombre, '') || ' ' || COALESCE(p.segundo_nombre, '') || ' ' || COALESCE(p.primer_apellido, '') || ' ' || COALESCE(p.segundo_apellido, '') AS nombre_responsable,"
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
                        "    a.estado = 'APROBADO' AND a.visible = true AND u.visible = true AND p.visible = true " +
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
                        "     COALESCE(pe.valor, 0) AS presupuesto_externo," +
                        "    p.primer_nombre || ' ' || p.primer_apellido AS nombre_responsable " +
                        "FROM " +
                        "    usuarios u " +
                        "JOIN " +
                        "    actividades a ON u.id = a.id_responsable " +
                        "LEFT JOIN " +
                        "    presupuesto_externo pe ON pe.id_actividad = a.id_actividad " +
                        "JOIN " +
                        "    persona p ON u.persona_id_persona = p.id_persona " +
                        "WHERE " +
                        "    a.estado = 'APROBADO' AND a.visible = true AND u.visible = true " +
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
                        "    a.recursos_propios," +
                        "    COALESCE(pex.valor, 0) AS presupuesto_externo," + 
                        "    a.estado " +
                        "FROM " +
                        "    actividades a " +
                        "LEFT JOIN " + 
                        "    presupuesto_externo pex ON a.id_actividad = pex.id_actividad " +
                        "JOIN " +
                        "    aprobacion_actividad apac ON a.id_actividad = apac.id_actividad " +
                        "JOIN " +
                        "    poa p ON apac.id_poa = p.id_poa " +
                        "WHERE " +
                        "    a.visible = true " +
                        "    AND p.id_poa = :id_Poa " +
                        "    AND a.estado = 'PENDIENTE' " +
                        "ORDER BY " +
                        "    a.id_actividad", nativeQuery = true)
        List<Object[]> obtenerDetalleActividadesAprob(@Param("id_Poa") Long id_Poa);

        // Actualizar el estado de las actividades relacionadas por id_poa
        @Modifying
        @Transactional
        @Query(value = "UPDATE actividades SET estado = :estado WHERE id_actividad IN (SELECT aa.id_actividad FROM aprobacion_actividad aa WHERE aa.id_poa = :poaId)", nativeQuery = true)
        void actualizarEstadoPorIdPoa(@Param("poaId") Long poaId, @Param("estado") String estado);

        @Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado "
                        +
                        "FROM actividades a " +
                        "WHERE a.visible = true AND a.id_responsable = :responsableId " +
                        "ORDER BY a.nombre ASC", nativeQuery = true)
        List<Object[]> listarActividadesPorIdResponsable(@Param("responsableId") Long responsableId);

        @Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado, a.estado,\n"
                        +
                        "    COALESCE(totalpresupuestoEterno, 0) AS totalpresupuestoEterno,\n" +
                        "    COALESCE(totalreformaSuplemento, 0) AS totalreformaSuplemento,\n" +
                        "    COALESCE(totalreformaTIncremento, 0) AS totalreformaTIncremento,\n" +
                        "    COALESCE(totalreformaTDecremento, 0) AS totalreformaTDecremento\n" +
                        "FROM actividades a\n" +
                        "JOIN poa p ON a.id_poa = p.id_poa\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT id_actividad, SUM(valor) AS totalpresupuestoEterno\n" +
                        "    FROM presupuesto_externo\n" +
                        "    GROUP BY id_actividad\n" +
                        ") pe ON a.id_actividad = pe.id_actividad\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT id_actividad, SUM(valor) AS totalreformaSuplemento\n" +
                        "    FROM reforma_suplemento\n" +
                        "    GROUP BY id_actividad\n" +
                        ") rs ON a.id_actividad = rs.id_actividad\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT id_actividad, SUM(valor) AS totalreformaTIncremento\n" +
                        "    FROM reforma_traspasoi\n" +
                        "    GROUP BY id_actividad\n" +
                        ") rti ON a.id_actividad = rti.id_actividad\n" +
                        "LEFT JOIN (\n" +
                        "    SELECT id_actividad, SUM(valor) AS totalreformaTDecremento\n" +
                        "    FROM reforma_traspasod\n" +
                        "    GROUP BY id_actividad\n" +
                        ") rtd ON a.id_actividad = rtd.id_actividad\n" +
                        "WHERE a.visible = true AND a.estado = 'APROBADO' AND p.id_poa = :poaId\n" +
                        "ORDER BY a.nombre ASC", nativeQuery = true)
        List<Object[]> listarActividadesConTotalPresupuestos(@Param("poaId") Long poaId);

        // query - actividades que tenga archivos rechazados
        @Query(value = "SELECT DISTINCT a.*\n" +
                        "FROM actividades a\n" +
                        "INNER JOIN archivo ar ON a.id_actividad = ar.id_actividad\n" +
                        "WHERE LOWER(ar.estado) = 'rechazado';\n", nativeQuery = true)
        List<Actividades> listarActEviRechazados();

        @Query(value = "SELECT u.id, u.username, pe.primer_nombre, pe.primer_apellido, pe.cargo, a.nombre " +
                        "FROM actividades a " +
                        "JOIN usuarios u ON a.id_responsable = u.id " +
                        "JOIN persona pe ON u.persona_id_persona = pe.id_persona " +
                        "WHERE a.id_actividad = :actividadId", nativeQuery = true)
        List<Object[]> listarUsuariosActividadID(@Param("actividadId") Long actividadId);

        // Query para obtener las actividades en estado pendiente
        @Query(value = "SELECT " +
                        "    a.id_actividad," +
                        "    a.nombre AS nombre_actividad," +
                        "    a.descripcion," +
                        "    a.codificado," +
                        "    a.devengado," +
                        "    a.recursos_propios," +
                        "    a.descripcion AS responsable " +
                        "from " +
                        "    actividades a " +
                        "JOIN " +
                        "    aprobacion_actividad apac ON a.id_actividad = apac.id_actividad " +
                        "JOIN " +
                        "    poa p ON apac.id_poa = p.id_poa " +
                        "WHERE " +
                        "    a.visible = true " +
                        "    AND p.id_poa = :id_Poa " +
                        "    AND a.estado = 'PENDIENTE' " +
                        "group by a.id_actividad " +
                        "ORDER BY " +
                        "    a.id_actividad", nativeQuery = true)
        List<ActividadesPendientesPorPoaProjection> ActividadesPendientesPorPoa(Long id_Poa);

        // Actualizar el estado de la actividad cuando se crea una nueva evaluacion o
        // aprobacion
        @Modifying
        @Transactional
        @Query(value = "UPDATE actividades " +
                        "SET estado = :estado " +
                        "WHERE id_actividad = :id_actividad", nativeQuery = true)
        void actualizarEstadoPorAprobacion(Long id_actividad, String estado);

        @Query(value = "select codificado-devengado  as valor from actividades where id_actividad=:idact", nativeQuery = true)
        valorprojec valoracti(Long idact);
        @Query(value = "SELECT ac.id_actividad, ac.codificado,ac.devengado,ac.presupuesto_referencial,ac.recursos_propios,ac.nombre,ac.descripcion FROM actividades ac JOIN poa po ON ac.id_poa = po.id_poa WHERE ac.id_responsable = :idres AND po.id_poa = :idpoa AND ac.estado = 'APROBADO';", nativeQuery = true)
        List<actividad_archi_projection> poaacti2(Long idres, Long idpoa);

        @Query(value =
                "SELECT ac.* " +
                        "FROM actividades ac " +
                        "INNER JOIN aprobacion_actividad aa ON ac.id_actividad = aa.id_actividad " +
                        "INNER JOIN poa po ON aa.id_poa = po.id_poa " +
                        "WHERE ac.id_responsable = :idres AND po.id_poa = :idpoa AND ac.estado = 'APROBADO'",
                nativeQuery = true)
        List<Actividades> poaacti(Long idres, Long idpoa);

        @Query(value = "SELECT id_actividad, codificado, descripcion, devengado,\n" +
                "estado, nombre, presupuesto_referencial, recursos_propios,\n" +
                "id_poa\n" +
                "FROM public.actividades\n" +
                "WHERE id_poa = :poaId AND visible= true", nativeQuery = true)
        List<Object[]> lista_de_ActividadesPorIdPoa(@Param("poaId") Long poaId);
        @Query(value = "SELECT CAST(fecha_fin AS DATE) FROM actividades WHERE id_actividad=:idact", nativeQuery = true)
        activ_fecha_lim_projection fechalim_act(Long idact);

}