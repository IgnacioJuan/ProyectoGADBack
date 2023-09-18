package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.projection.PoaNoAprobadoProjection;
import com.sistema.examenes.projection.PoaporFechaRepoProjection;
import com.sistema.examenes.projection.Poaactiprojection;
import com.sistema.examenes.projection.PoasConActividadesPendientesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoaRepository extends JpaRepository<Poa, Long> {

    @Query(value = "SELECT * from poa where visible =true ORDER BY fecha_inicio desc", nativeQuery = true)
    List<Poa> listarPoas();

    @Query(value = "SELECT * FROM poa WHERE id_poa= :id AND visible = true", nativeQuery = true)
    Poa obtenerPoaId(@Param("id") Long id);

    @Query(value = "SELECT p.* " +
            "from poa p " +
            "join aprobacion_poa ap " +
            "on ap.id_poa = p.id_poa " +
            "where p.visible =true " +
            "and ap.id_proyecto = :id_proyecto " +
            "and (p.estado= :estado or :estado is null) " +
            "group by p.id_poa " +
            "ORDER BY fecha_inicio desc", nativeQuery = true)
    List<Poa> listarPoadelProyectoconEstado(Long id_proyecto, String estado);

    /*@Query(value = "SELECT p.id_poa, p.fecha_inicio, p.fecha_fin, p.localizacion, p.cobertura, p.barrio, p.comunidad, p.linea_base, p.tipo_periodo "
            + "FROM poa p "
            + "JOIN aprobacion_poa ap ON p.id_poa = ap.id_poa "
            + "JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto "
            + "WHERE p.estado = 'APROBADO' AND p.visible = true AND pr.id_modelo_poa = (SELECT MAX(m.id_modelo_poa) FROM modelopoa m WHERE m.visible = true and m.estado='ACTIVO')", nativeQuery = true)
    List<Object[]> listarPoasDeModelo();*/

    //QUERY para listar poas con nombre de proyecto del modelo activo, con fitros de fechas
    @Query(value = "SELECT p.id_poa, pr.id_proyecto, pr.nombre as nombreProyecto, p.meta_planificada,p.tipo_periodo, p.fecha_inicio, p.fecha_fin " +
            "FROM poa p " +
            "JOIN aprobacion_poa ap ON p.id_poa = ap.id_poa " +
            "JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto " +
            "JOIN modelopoa m ON pr.id_modelo_poa = m.id_modelo_poa " +
            "WHERE p.estado = 'APROBADO' AND p.visible = true AND m.estado = 'ACTIVO' " +
            "AND NOW() BETWEEN p.fecha_inicio AND p.fecha_fin;" , nativeQuery = true)
    List<Object[]> listarPoasProyectoDeModeloFiltroFechas();

    @Query(value = "SELECT DISTINCT p.id_poa, p.fecha_inicio, p.fecha_fin,\n"
            + "    p.id_responsable, ap.estado,\n"
            + "    ap.observacion, ap.fecha_aprobacion, pr.nombre,\n"
            + "    ap.id_usuario, u.username,\n"
            + "    per.primer_nombre, per.primer_apellido\n"
            + "FROM poa p \n"
            + "INNER JOIN aprobacion_poa ap ON p.id_poa = ap.id_poa\n"
            + "INNER JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto\n"
            + "INNER JOIN usuarios u ON ap.id_usuario = u.id\n"
            + "INNER JOIN persona per ON u.persona_id_persona = per.id_persona\n"
            + "WHERE ap.estado = 'RECHAZADO';", nativeQuery = true)
    List<PoaNoAprobadoProjection> findNoAprobados();

    
    
    // @Query(value= "SELECT u.id , u.username, p.localizacion,"
    //         + " p.barrio, pr.nombre, ap.estado "
    //         + " FROM usuarios u INNER JOIN poa p ON "
    //         + "u.id = p.id_responsable INNER JOIN aprobacion_poa ap ON p.id_poa "
    //         + "= ap.id_poa INNER JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto "
    //         + "GROUP BY u.id, p.id_poa, p.localizacion, p.fecha_inicio, pr.nombre, ap.estado", nativeQuery = true)
    // List<PoaporUsuarioProjection> findPoaporUsuario(); 
 @Query(value = "SELECT * from poa where visible = true ",nativeQuery = true)
 List<Poa> listarPoasjohn();


    @Query(value = "SELECT\n" +
            "   pr.nombre,\n" +
            "    p.id_poa,\n" +
            "    p.barrio,\n" +
            "    p.cobertura,\n" +
            "    p.comunidad,\n" +
            "\tp.fecha_inicio,\n" +
            "    p.fecha_fin,\n" +
            "    p.estado,\n" +
            "    p.localizacion\n" +
            "FROM poa p\n" +
            "INNER JOIN aprobacion_poa a ON p.id_poa = a.id_poa\n" +
            "INNER JOIN proyecto pr ON a.id_proyecto = pr.id_proyecto\n" +
            "WHERE p.id_responsable = :idResponsable\n" +
            "    AND p.estado = :estado\n" +
            "    AND p.visible = true AND a.visible=true AND pr.visible=true", nativeQuery = true)
    List<Object[]> listarPoasPorAdminEstado(Long idResponsable, String estado);

    
   

    @Query(value = "SELECT mpdot.*,(mpdot.meta_alcanzar / mpdot.meta_planificada) * 100 AS valor_total\n" +
        "FROM poa mpdot", nativeQuery = true)
List<Poa> listarPoasPromedio();


    //Obtener los poa con actividades pendientes
    @Query(value = "select A.*,  " +
            "COUNT(DISTINCT c.id_actividad) as nro_actividades, " +
            "E.nombre, e.codigo, " +
            "concat(g.primer_nombre, ' ', g.primer_apellido) as usuario " +
            "from POA A " +
            "join aprobacion_actividad B " +
            "on A.id_poa = B.id_poa " +
            "join actividades c " +
            "on C.id_actividad = B.id_actividad " +
            "join aprobacion_poa d " +
            "on d.id_poa = A.id_poa " +
            "join proyecto E " +
            "on E.id_proyecto = d.id_proyecto " +
            "join usuarios f " +
            "on f.id = a.id_responsable " +
            "join persona g " +
            "on g.id_persona = f.persona_id_persona " +
            "where C.estado = 'PENDIENTE' " +
            "and c.visible = true " +
            "and a.visible = true " +
            "and a.estado ='APROBADO' " +
            "group by a.id_poa, e.id_proyecto, g.id_persona "
            , nativeQuery = true)
    List<PoasConActividadesPendientesProjection> PoasConActividadesPendientes();

//Listar los poas aporbados segun el admin, para evaluarlos
    @Query(value = "SELECT\n" +
            "   pr.nombre,\n" +
            "    p.id_poa,\n" +
            "    p.barrio,\n" +
            "    p.cobertura,\n" +
            "    p.comunidad,\n" +
            "\tp.fecha_inicio,\n" +
            "    p.fecha_fin,\n" +
            "    p.estado,\n" +
            "    p.localizacion,\n" +
            "    p.meta_alcanzar,\n " +
            "    p.meta_planificada,\n" +
            "    p.tipo_periodo " +
            "FROM poa p\n" +
            "INNER JOIN proyecto pr ON p.id_proyecto = pr.id_proyecto\n" +
            "WHERE (:idResponsable = -1 OR p.id_responsable = :idResponsable)\n" +
            "    AND p.estado = 'APROBADO'\n" +
            "    AND p.visible = true \n" +
            "    AND pr.visible=true \n" +
            "    and CURRENT_DATE between p.fecha_inicio and p.fecha_fin ", nativeQuery = true)
    List<PoaporFechaRepoProjection> listarPoaApAdm(Long idResponsable);

    // @Query(value = "select A.*,  "
    //         + "COUNT(DISTINCT c.id_actividad) as nro_actividades, "
    //         + "E.nombre "
    //         + "from POA A "
    //         + "join aprobacion_actividad B "
    //         + "on A.id_poa = B.id_poa "
    //         + "join actividades c "
    //         + "on C.id_actividad = B.id_actividad "
    //         + "join aprobacion_poa d "
    //         + "on d.id_poa = A.id_poa "
    //         + "join proyecto E "
    //         + "on E.id_proyecto = d.id_proyecto "
    //         + "where C.estado = 'PENDIENTE' "
    //         + "and c.visible = true "
    //         + "and a.visible = true "
    //         + "group by a.id_poa, e.id_proyecto ",
    //         nativeQuery = true)
    // List<PoasConActividadesPendientesProjection> PoasConActividadesPendientes();
    @Query(value = "SELECT distinct p.id_proyecto, poa.id_poa, p.nombre AS nombre_proyecto, poa.meta_planificada\n" +
            "FROM public.poa poa join actividades ac on ac.id_poa = poa.id_poa\n" +
            "JOIN public.proyecto p ON poa.id_proyecto = p.id_proyecto\n" +
            "WHERE ac.id_responsable =:id and poa.visible=true;\n", nativeQuery = true)
    List<Poaactiprojection> poaacjq(Long id);

//Listar Poas con solicitudes de presupuesto
    @Query(value = "SELECT DISTINCT pr.nombre AS nombre_proyecto, p.id_poa, p.barrio, p.cobertura, p.comunidad, p.estado AS estado_poa, p.meta_alcanzar, p.meta_planificada\n" +
            "FROM public.poa AS p\n" +
            "INNER JOIN public.proyecto AS pr ON p.id_proyecto = pr.id_proyecto\n" +
            "INNER JOIN public.aprobacion_poa AS ap ON p.id_poa = ap.id_poa\n" +
            "INNER JOIN public.solicitud_presupuesto AS sol ON p.id_poa = sol.id_poa\n" +
            "WHERE p.estado = 'APROBADO'\n" +
            "    AND ap.estado = 'APROBADO'\n" +
            "    AND p.visible = true\n" +
            "    AND ap.visible = true\n" +
            "    AND sol.estado = 'PENDIENTE'\n" +
            "    AND sol.visible = true\n" +
            "    AND sol.id_superadmin =:idAdmin\n" +
            "    AND pr.id_modelo_poa = (SELECT MAX(m.id_modelo_poa) FROM modelopoa m WHERE m.visible = true and m.estado = 'ACTIVO');", nativeQuery = true)
    List<Object[]> listarPoasPorSolicitudPresupuesto(Long idAdmin);
    //Listar Poas con Porcentajes Indicadores


    @Query(value ="\n" +
            "SELECT pr.nombre AS nombre_proyecto, p.id_poa, p.localizacion,  p.tipo_periodo,\n" +
            "    p.linea_base, p.meta_alcanzar, p.meta_planificada, i.tipo_evaluacion,  m.nombre AS nombre_metapdot,\n" +
            "    CASE\n" +
            "        WHEN i.tipo_evaluacion = 'DECRECIENTE' THEN\n" +
            "            CAST((p.linea_base - p.meta_alcanzar) / (p.linea_base - p.meta_planificada) * 100 AS numeric(10, 2))\n" +
            "\t\t WHEN i.tipo_evaluacion = 'CRECIENTE' THEN\n" +
            "           CAST((p.meta_alcanzar / p.meta_planificada) * 100   AS numeric(10, 2))\n" +
            "        ELSE\n" +
            "          0\n" +
            "    END AS porcentaje_cumplimiento\n" +
            "FROM public.poa AS p\n" +
            "INNER JOIN public.proyecto AS pr ON p.id_proyecto = pr.id_proyecto\n" +
            "INNER JOIN public.indicador AS i ON pr.id_indicador = i.id_indicador\n" +
            "LEFT JOIN public.metapdot AS m ON i.id_meta_pdot = m.id_meta_pdot \n" +
            "WHERE p.visible = true AND i.visible = true AND m.visible = true;", nativeQuery = true)
    List<Object[]> listarPoasMetasIndicadores();


}
