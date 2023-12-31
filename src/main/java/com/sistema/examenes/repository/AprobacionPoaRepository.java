package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionPoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AprobacionPoaRepository extends JpaRepository<AprobacionPoa, Long> {

    @Query(value = "SELECT * from aprobacion_poa where visible = true", nativeQuery = true)
    List<AprobacionPoa> listarAprobacionPoa();

    @Query("SELECT a FROM AprobacionPoa a WHERE a.poa.id = :idPoa")
    AprobacionPoa findByPoaId(@Param("idPoa") Long idPoa);

    /*@Query(value = "SELECT id_aprobacionpoa, estado, observacion\n" +
            "FROM  aprobacion_poa\n" +
            "WHERE id_poa  = :idPoa  AND  visible=true;", nativeQuery = true)
    List<Object[]> listarAprobacionPoaPorIdPoa(@Param("idPoa") Long idPoa);*/

    @Query(value = "SELECT ap.observacion, ap.estado, ap.id_aprobacionpoa, p.primer_nombre, p.primer_apellido, ap.fecha_aprobacion \n" +
            "FROM public.aprobacion_poa ap\n" +
            "INNER JOIN public.usuarios u ON ap.id_usuario = u.id\n" +
            "INNER JOIN public.persona p ON u.persona_id_persona = p.id_persona\n" +
            "WHERE ap.id_poa =:idPoa AND ap.visible= true AND u.visible=true AND p.visible=true;\n", nativeQuery = true)
    List<Object[]> listarAprobacionPoaPorIdPoa(@Param("idPoa") Long idPoa);

//    @Query(value = " SELECT * FROM aprobacion_poa", nativeQuery = true)
//    List<AprobacionPoa> findPoaporUsuario();
    @Query(value = "SELECT  u.id, u.username,per.primer_nombre, ap.id_aprobacionpoa ,per.primer_apellido, p.id_poa,progra.nombre,\n"
            + " pro.nombre,pro.id_proyecto,ap.id_usuario,ap.visible,p.estado, ap.observacion, ap.fecha_aprobacion FROM usuarios u\n"
            + "INNER JOIN poa p ON u.id = p.id_responsable\n"
            + "INNER JOIN aprobacion_poa  ap ON p.id_poa = ap.id_poa\n"
            + "INNER JOIN proyecto pro ON ap.id_proyecto = pro.id_proyecto\n"
            + "INNER JOIN persona per ON u.id = per.id_persona\n"
            + "INNER JOIN programa progra ON pro.id_programa = progra.id_programa\n"
            + "where  pro.id_proyecto =:id_proyecto and p.estado = 'APROBADO'", nativeQuery = true)
    List<AprobacionPoa> findPoaporUsuario(@Param("id_proyecto") Long id_proyecto);

    /**
     * ***** MODULO APROBACION POA c.c*******
     */
    @Query(value = "SELECT "
            + "p.id_poa, "
            + "prg.nombre AS direccion_departamental, "
            + "per.primer_nombre || ' ' || per.primer_apellido AS responsable_super_admin, "
            + "per.correo, "
            + "pr.area, "
            + "pr.fecha_inicio AS fecha_inicio, "
            + "pr.fecha_fin AS fecha_fin, "
            + "p.fecha_creacion AS fecha_creacion, "
            + "pr.codigo AS codigo_proyecto, "
            + "pr.nombre AS nombre_proyecto, "
            + "pr.descripcion, "
            + "cm.nombre AS nombre_componente, "
            + "os.nombre AS nombre_ods, "
            + "op.nombre AS nombre_pnd, "
            + "opt.nombre AS nombre_pdot, "
            + "pr.objetivo AS objetivo_proyecto, "
            + "ind.nombre AS nombre_indicador, "
            + "mtp.nombre AS nombre_metapdot, "
            + "pr.meta AS meta_proyecto, "
            + "UPPER(CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido)) AS nombre_completo, "
            + "p.meta_planificada, "
            + "p.linea_base, "
            + "p.cobertura, "
            + "p.localizacion, "
            + "p.barrio, "
            + "p.comunidad, "
            + "p.tipo_periodo, "
            + "per.cargo "
            + "FROM aprobacion_poa aa "
            + "JOIN poa p ON aa.id_poa = p.id_poa "
            + "JOIN proyecto pr ON aa.id_proyecto = pr.id_proyecto "
            + "JOIN objetivoods os ON pr.id_ods = os.id_objetivo_ods "
            + "JOIN objetivopnd op ON pr.id_pnd = op.id_objetivo_pnd "
            + "JOIN programa prg ON pr.id_programa = prg.id_programa "
            + "JOIN indicador ind ON pr.id_indicador = ind.id_indicador "
            + "JOIN metapdot mtp ON ind.id_meta_pdot = mtp.id_meta_pdot "
            + "JOIN objetivopdot opt ON mtp.id_objetivo_pdot = opt.id_objetivo_pdot "
            + "JOIN componente cm ON opt.id_componente = cm.id_componente "
            + "JOIN usuarios u ON p.id_responsable = u.id "
            + "JOIN persona per ON u.persona_id_persona = per.id_persona "
            + "WHERE aa.visible = true "
            + "AND p.estado = 'PENDIENTE'", nativeQuery = true)
    List<Object[]> obtenerPoasCompletos();

    @Query(value = "SELECT "
            + "p.id_poa, "
            + "prg.nombre AS direccion_departamental, "
            + "per.primer_nombre || ' ' || per.primer_apellido AS responsable_super_admin, "
            + "per.correo, "
            + "pr.area, "
            + "pr.fecha_inicio AS fecha_inicio, "
            + "pr.fecha_fin AS fecha_fin, "
            + "p.fecha_creacion AS fecha_creacion, "
            + "pr.codigo AS codigo_proyecto, "
            + "pr.nombre AS nombre_proyecto, "
            + "pr.descripcion, "
            + "cm.nombre AS nombre_componente, "
            + "os.nombre AS nombre_ods, "
            + "op.nombre AS nombre_pnd, "
            + "opt.nombre AS nombre_pdot, "
            + "pr.objetivo AS objetivo_proyecto, "
            + "ind.nombre AS nombre_indicador, "
            + "mtp.nombre AS nombre_metapdot, "
            + "pr.meta AS meta_proyecto, "
            + "UPPER(CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido)) AS nombre_completo, "
            + "p.meta_planificada, "
            + "p.linea_base, "
            + "p.cobertura, "
            + "p.localizacion, "
            + "p.barrio, "
            + "p.comunidad, "
            + "p.tipo_periodo, "
            + "per.cargo "
            + "FROM aprobacion_poa aa "
            + "JOIN poa p ON aa.id_poa = p.id_poa "
            + "JOIN proyecto pr ON aa.id_proyecto = pr.id_proyecto "
            + "JOIN objetivoods os ON pr.id_ods = os.id_objetivo_ods "
            + "JOIN objetivopnd op ON pr.id_pnd = op.id_objetivo_pnd "
            + "JOIN programa prg ON pr.id_programa = prg.id_programa "
            + "JOIN indicador ind ON pr.id_indicador = ind.id_indicador "
            + "JOIN metapdot mtp ON ind.id_meta_pdot = mtp.id_meta_pdot "
            + "JOIN objetivopdot opt ON mtp.id_objetivo_pdot = opt.id_objetivo_pdot "
            + "JOIN componente cm ON opt.id_componente = cm.id_componente "
            + "JOIN usuarios u ON p.id_responsable = u.id "
            + "JOIN persona per ON u.persona_id_persona = per.id_persona "
            + "WHERE aa.visible = true and p.id_poa= :idPoa", nativeQuery = true)
    List<Object[]> obtenerPoaCompletoPorId(@Param("idPoa") Long id_poa);
}
