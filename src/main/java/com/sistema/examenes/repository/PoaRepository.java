package com.sistema.examenes.repository;

import com.sistema.examenes.dto.PoaNoAprobadoDTO;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.projection.PoaNoAprobadoProjection;
import com.sistema.examenes.projection.PoaporUsuarioProjection;
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
            "and (ap.estado= :estado or :estado is null)" +
            "ORDER BY fecha_inicio desc", nativeQuery = true)
    List<Poa> listarPoadelProyectoconEstado(Long id_proyecto, String estado);

    @Query(value = "SELECT p.id_poa, p.fecha_inicio, p.fecha_fin, p.localizacion, p.cobertura, p.barrio, p.comunidad, p.linea_base, p.tipo_periodo " + 
            "FROM poa p " +
            "JOIN aprobacion_poa ap ON p.id_poa = ap.id_poa " +
            "JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto " +
            "WHERE p.estado = 'APROBADO' AND p.visible = true AND pr.id_modelo_poa = (SELECT MAX(m.id_modelo_poa) FROM modelopoa m WHERE m.visible = true)", nativeQuery = true)
    List<Object[]> listarPoasDeModelo();
 
    @Query(value= "SELECT p.id_poa,p.fecha_inicio, p.localizacion, p.barrio, p.comunidad,"
            + " ap.estado, ap.observacion, pr.nombre FROM poa p INNER JOIN aprobacion_poa ap ON"
            + " p.id_poa = ap.id_poa INNER JOIN proyecto pr ON pr.id_proyecto = pr.id_proyecto WHERE ap.estado != 'APROBADO'  AND ap.estado != 'PENDIENTE'", nativeQuery = true)
    List<PoaNoAprobadoProjection> findNoAprobados(); 
    
    

    
    
    @Query(value= "SELECT u.id , u.username, p.localizacion,"
            + " p.barrio, pr.nombre, ap.estado "
            + " FROM usuarios u INNER JOIN poa p ON "
            + "u.id = p.id_responsable INNER JOIN aprobacion_poa ap ON p.id_poa "
            + "= ap.id_poa INNER JOIN proyecto pr ON ap.id_proyecto = pr.id_proyecto "
            + "GROUP BY u.id, p.id_poa, p.localizacion, p.fecha_inicio, pr.nombre, ap.estado", nativeQuery = true)
    List<PoaporUsuarioProjection> findPoaporUsuario();


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
            "E.nombre " +
            "from POA A " +
            "join aprobacion_actividad B " +
            "on A.id_poa = B.id_poa " +
            "join actividades c " +
            "on C.id_actividad = B.id_actividad " +
            "join aprobacion_poa d " +
            "on d.id_poa = A.id_poa " +
            "join proyecto E " +
            "on E.id_proyecto = d.id_proyecto " +
            "where C.estado = 'PENDIENTE' " +
            "and c.visible = true " +
            "and a.visible = true " +
            "group by a.id_poa, e.id_proyecto "
            , nativeQuery = true)
    List<PoasConActividadesPendientesProjection> PoasConActividadesPendientes();

}