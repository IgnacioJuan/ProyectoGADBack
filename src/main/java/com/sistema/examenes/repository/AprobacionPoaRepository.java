package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionPoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AprobacionPoaRepository extends JpaRepository<AprobacionPoa, Long> {

        @Query(value = "SELECT * from aprobacion_poa where visible = true", nativeQuery = true)
        List<AprobacionPoa> listarAprobacionPoa();

        @Query(value = "SELECT " +
                        "p.id_poa, " +
                        "prg.nombre AS direccion_departamental, " +
                        "per.primer_nombre || ' ' || per.primer_apellido AS responsable_super_admin, " +
                        "per.correo, " +
                        "pr.area, " +
                        "pr.fecha_inicio AS fecha_inicio, " +
                        "pr.fecha_fin AS fecha_fin, " +
                        "pr.codigo AS codigo_proyecto, " +
                        "pr.nombre AS nombre_proyecto, " +
                        "pr.descripcion, " +
                        "cm.nombre AS nombre_componente, " +
                        "os.nombre AS nombre_ods, " +
                        "op.nombre AS nombre_pnd, " +
                        "opt.nombre AS nombre_pdot, " +
                        "pr.objetivo AS objetivo_proyecto, " +
                        "ind.nombre AS nombre_indicador, " +
                        "mtp.nombre AS nombre_metapdot, " +
                         "pr.meta AS meta_proyecto, " +
                        "UPPER(CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido)) AS nombre_completo, "+
                        "p.linea_base, " +
                        "p.cobertura, " +
                        "p.localizacion, " +
                        "p.barrio, " +
                        "p.comunidad, " +
                        "p.tipo_periodo, " +
                        "per.cargo " +
                        "FROM aprobacion_poa aa " +
                        "JOIN poa p ON aa.id_poa = p.id_poa " +
                        "JOIN proyecto pr ON aa.id_proyecto = pr.id_proyecto " +
                        "JOIN objetivoods os ON pr.id_ods = os.id_objetivo_ods " +
                        "JOIN objetivopnd op ON pr.id_pnd = op.id_objetivo_pnd " +
                        "JOIN programa prg ON pr.id_programa = prg.id_programa " +
                        "JOIN indicador ind ON pr.id_indicador = ind.id_indicador " +
                        "JOIN metapdot mtp ON ind.id_meta_pdot = mtp.id_meta_pdot " +
                        "JOIN objetivopdot opt ON mtp.id_objetivo_pdot = opt.id_objetivo_pdot " +
                        "JOIN componente cm ON opt.id_componente = cm.id_componente " +
                        "JOIN usuarios u ON p.id_responsable = u.id " +
                        "JOIN persona per ON u.persona_id_persona = per.id_persona " +
                        "JOIN aprobacion_actividad apact ON p.id_poa = apact.id_poa " +
                        "JOIN actividades act ON apact.id_actividad = act.id_actividad " +
                        "JOIN presupuesto_externo pext ON act.id_actividad = pext.id_actividad " +
                        "WHERE aa.visible = true " +
                        "AND p.estado = 'PENDIENTE'", nativeQuery = true)
        List<Object[]> obtenerAprobacionesPoa();

        @Query(value = "SELECT " +
                        "p.id_poa, " +
                        "prg.nombre AS direccion_departamental, " +
                        "per.primer_nombre || ' ' || per.primer_apellido AS responsable_super_admin, " +
                        "per.correo, " +
                        "pr.area, " +
                        "pr.fecha_inicio AS fecha_inicio, " +
                        "pr.fecha_fin AS fecha_fin, " +
                        "pr.codigo AS codigo_proyecto, " +
                        "pr.nombre AS nombre_proyecto, " +
                        "pr.descripcion, " +
                        "cm.nombre AS nombre_componente, " +
                        "os.nombre AS nombre_ods, " +
                        "op.nombre AS nombre_pnd, " +
                        "opt.nombre AS nombre_pdot, " +
                        "pr.objetivo AS objetivo_proyecto, " +
                        "ind.nombre AS nombre_indicador, " +
                        "mtp.nombre AS nombre_metapdot, " +
                         "pr.meta AS meta_proyecto, " +
                        "UPPER(CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido)) AS nombre_completo, "+
                        "p.linea_base, " +
                        "p.cobertura, " +
                        "p.localizacion, " +
                        "p.barrio, " +
                        "p.comunidad, " +
                        "p.tipo_periodo, " +
                        "per.cargo " +
                        "FROM aprobacion_poa aa " +
                        "JOIN poa p ON aa.id_poa = p.id_poa " +
                        "JOIN proyecto pr ON aa.id_proyecto = pr.id_proyecto " +
                        "JOIN objetivoods os ON pr.id_ods = os.id_objetivo_ods " +
                        "JOIN objetivopnd op ON pr.id_pnd = op.id_objetivo_pnd " +
                        "JOIN programa prg ON pr.id_programa = prg.id_programa " +
                        "JOIN indicador ind ON pr.id_indicador = ind.id_indicador " +
                        "JOIN metapdot mtp ON ind.id_meta_pdot = mtp.id_meta_pdot " +
                        "JOIN objetivopdot opt ON mtp.id_objetivo_pdot = opt.id_objetivo_pdot " +
                        "JOIN componente cm ON opt.id_componente = cm.id_componente " +
                        "JOIN usuarios u ON p.id_responsable = u.id " +
                        "JOIN persona per ON u.persona_id_persona = per.id_persona " +
                        "JOIN aprobacion_actividad apact ON p.id_poa = apact.id_poa " +
                        "JOIN actividades act ON apact.id_actividad = act.id_actividad " +
                        "JOIN presupuesto_externo pext ON act.id_actividad = pext.id_actividad " +
                        "WHERE aa.visible = true and p.id_poa= :idPoa", nativeQuery = true)
        List<Object[]> obtenerAprobacionPoaPorId(@Param("idPoa") Long id_poa);

        @Query("SELECT a FROM AprobacionPoa a WHERE a.poa.id = :idPoa")
        AprobacionPoa findByPoaId(@Param("idPoa") Long idPoa);

    /*@Query(value = "SELECT id_aprobacionpoa, estado, observacion\n" +
            "FROM  aprobacion_poa\n" +
            "WHERE id_poa  = :idPoa  AND  visible=true;", nativeQuery = true)
    List<Object[]> listarAprobacionPoaPorIdPoa(@Param("idPoa") Long idPoa);*/

    @Query(value = "SELECT ap.observacion, ap.estado, ap.id_aprob acionpoa, p.primer_nombre, p.primer_apellido, ap.fecha_aprobacion \n" +
            "FROM public.aprobacion_poa ap\n" +
            "INNER JOIN public.usuarios u ON ap.id_usuario = u.id\n" +
            "INNER JOIN public.persona p ON u.persona_id_persona = p.id_persona\n" +
            "WHERE ap.id_poa = 1 AND ap.visible= true AND u.visible=true AND p.visible=true;\n", nativeQuery = true)
    List<Object[]> listarAprobacionPoaPorIdPoa(@Param("idPoa") Long idPoa);
}
