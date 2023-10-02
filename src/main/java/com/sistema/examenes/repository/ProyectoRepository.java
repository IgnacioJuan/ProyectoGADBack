package com.sistema.examenes.repository;

import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    @Query(value = "SELECT * from proyecto where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Proyecto> listarProyectos();

    @Query(value = "  SELECT * FROM proyecto p WHERE p.visible = true AND p.id_modelo_poa =:id_modelo_poa  ORDER BY  p.nombre ASC ", nativeQuery = true)
    List<Proyecto> listarProyectosdelModelo(Long id_modelo_poa);
    
//    @Query(value = " SELECT p.codigo, p.id_proyecto,p.meta,p.area, p.fecha_fin,p.porcentaje_alcance,p.visible,p.fecha_inicio,p.id_programa,p.id_indicador,\n" +
//"p.id_modelo_poa,p.objetivo,p.id_ods,p.id_pnd,p.nombre,\n" +
//"obj_ods.nombre, obj_pnd.nombre, obj_pdot.nombre,\n" +
//"ind.nombre, meta_pdot.nombre,prog.nombre,comp.nombre, compe.id_competencia\n" +
//"FROM public.proyecto AS p\n" +
//"JOIN public.programa AS prog ON prog.id_programa = p.id_programa\n" +
//"JOIN public.indicador AS ind ON ind.id_indicador = p.id_indicador\n" +
//"JOIN public.competencia AS compe ON compe.id_competencia = p.id_competencia\n" +
//"JOIN public.objetivoods AS obj_ods ON obj_ods.id_objetivo_ods = p.id_ods\n" +
//"JOIN public.metapdot AS meta_pdot ON meta_pdot.id_meta_pdot = ind.id_meta_pdot\n" +
//"JOIN public.objetivopdot AS obj_pdot ON obj_pdot.id_objetivo_pdot = meta_pdot.id_objetivo_pdot\n" +
//"JOIN public.componente AS comp ON comp.id_componente = obj_pdot.id_componente\n" +
//"JOIN public.objetivopnd AS obj_pnd ON obj_pnd.id_objetivo_pnd = p.id_pnd\n" +
//"JOIN public.modelopoa AS model ON model.id_modelo_poa = p.id_modelo_poa \n" +
//"JOIN public.usuarios AS us ON us.id = model.id_super_admin\n" +
//"JOIN public.persona AS per ON per.id_persona = us.persona_id_persona\n" +
//"WHERE  p.visible = true AND p.id_modelo_poa =:id_modelo_poa  ORDER BY  p.nombre ASC ", nativeQuery = true)
//    List<Proyecto> exportarexcel(Long id_modelo_poa);
    
       @Query(value = " SELECT * FROM proyecto p WHERE p.visible = true AND p.id_modelo_poa =:id_modelo_poa  ORDER BY  p.nombre ASC  ", nativeQuery = true)
       List<Proyecto>exportarexcel(Long id_modelo_poa);
    
    

    @Query(value = "SELECT p.id_proyecto,p.codigo,p.meta,p.nombre FROM public.proyecto p join public.modelopoa ON modelopoa.id_modelo_poa = p.id_modelo_poa  WHERE modelopoa.estado='ACTIVO' AND p.visible=true ORDER BY p.nombre", nativeQuery = true)
    List<Object[]> listProjectsActives();

    //create query for this query SELECT p.id_proyecto,p.codigo,p.meta,p.nombre FROM public.proyecto p join public.modelopoa ON modelopoa.id_modelo_poa = p.id_modelo_poa
    //WHERE modelopoa.estado='ACTIVO' AND p.visible=true ORDER BY p.nombre;
    @Query(value = "SELECT p.id_proyecto,p.codigo,p.meta,p.nombre FROM public.proyecto p join public.modelopoa ON modelopoa.id_modelo_poa = p.id_modelo_poa JOIN public.programa ON programa.id_programa = p.id_programa JOIN public.usuarios ON usuarios.id_programa = programa.id_programa WHERE modelopoa.estado='ACTIVO' AND p.visible=true AND usuarios.id=:id_usuario  ORDER BY p.nombre", nativeQuery = true)
    List<Object[]> listActiveProjects(Long id_usuario);

    
     @Query(value = "SELECT p.nombre AS nombreProyecto, SUM(a.codificado) AS montoCodificado, SUM(a.devengado) AS montoDevengado, CAST((SUM(a.devengado) / SUM(a.codificado)) * 100 AS numeric(10, 2)) AS pEjecucion FROM proyecto p INNER JOIN poa po ON p.id_proyecto = po.id_proyecto INNER JOIN actividades a ON po.id_poa = a.id_poa WHERE po.estado = 'APROBADO' AND p.visible = true AND po.visible = true AND a.visible = true GROUP BY p.nombre", nativeQuery = true)
    List<Object[]> listarProyectosReporte();

    
    
    
//SELECT
//    p.id_proyecto,
//    p.area,
//    per.cargo,
//    p.nombre,
//    p.codigo,
//    comp.nombre AS nombre_componente,
//    obj_ods.nombre AS nombre_objetivo_ods,
//    obj_pnd.nombre AS nombre_objetivo_pnd,
//    obj_pdot.nombre AS nombre_objetivo_pdot,
//    p.objetivo,
//    ind.nombre AS nombre_indicador,
//    meta_pdot.nombre AS nombre_meta_pdot,
//    prog.nombre AS nombre_programa,
//    UPPER(CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido)) AS nombre_completo,
//    CASE
//        WHEN p.fecha_inicio IS NOT NULL AND p.fecha_fin IS NOT NULL THEN
//            EXTRACT(YEAR FROM p.fecha_inicio) || '-' || EXTRACT(YEAR FROM p.fecha_fin)
//        ELSE
//            ''
//    END AS rango_fechas
//FROM public.proyecto AS p
//JOIN public.programa AS prog ON prog.id_programa = p.id_programa
//JOIN public.indicador AS ind ON ind.id_indicador = p.id_indicador
//JOIN public.objetivoods AS obj_ods ON obj_ods.id_objetivo_ods = p.id_ods
//JOIN public.metapdot AS meta_pdot ON meta_pdot.id_meta_pdot = ind.id_meta_pdot
//JOIN public.objetivopdot AS obj_pdot ON obj_pdot.id_objetivo_pdot = meta_pdot.id_objetivo_pdot
//JOIN public.componente AS comp ON comp.id_componente = obj_pdot.id_componente
//JOIN public.objetivopnd AS obj_pnd ON obj_pnd.id_objetivo_pnd = p.id_pnd
//JOIN public.modelopoa AS model ON model.id_modelo_poa = p.id_modelo_poa
//JOIN public.usuarios AS us ON us.id = model.id_super_admin
//JOIN public.persona AS per ON per.id_persona = us.persona_id_persona
//WHERE p.id_proyecto = 2;
    @Query(value = "SELECT p.id_proyecto,model.id_super_admin,p.area,per.cargo,p.nombre,p.codigo,comp.nombre AS nombre_componente,obj_ods.nombre AS nombre_objetivo_ods,obj_pnd.nombre AS nombre_objetivo_pnd,obj_pdot.nombre AS nombre_objetivo_pdot,p.objetivo,ind.nombre AS nombre_indicador,meta_pdot.nombre AS nombre_meta_pdot,prog.nombre AS nombre_programa,UPPER(CONCAT(per.primer_nombre, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido)) AS nombre_completo,CASE WHEN p.fecha_inicio IS NOT NULL AND p.fecha_fin IS NOT NULL THEN EXTRACT(YEAR FROM p.fecha_inicio) || '-' || EXTRACT(YEAR FROM p.fecha_fin) ELSE '' END AS rango_fechas FROM public.proyecto AS p JOIN public.programa AS prog ON prog.id_programa = p.id_programa JOIN public.indicador AS ind ON ind.id_indicador = p.id_indicador JOIN public.objetivoods AS obj_ods ON obj_ods.id_objetivo_ods = p.id_ods JOIN public.metapdot AS meta_pdot ON meta_pdot.id_meta_pdot = ind.id_meta_pdot JOIN public.objetivopdot AS obj_pdot ON obj_pdot.id_objetivo_pdot = meta_pdot.id_objetivo_pdot JOIN public.componente AS comp ON comp.id_componente = obj_pdot.id_componente JOIN public.objetivopnd AS obj_pnd ON obj_pnd.id_objetivo_pnd = p.id_pnd JOIN public.modelopoa AS model ON model.id_modelo_poa = p.id_modelo_poa JOIN public.usuarios AS us ON us.id = model.id_super_admin JOIN public.persona AS per ON per.id_persona = us.persona_id_persona WHERE p.id_proyecto = :id_proyecto", nativeQuery = true)
    List<Object[]> ProjectById(Long id_proyecto);

    //Obtener el siguiente codigo para el proyecto, tomando como referencia el numero
    //de proyectos que ya usan ese componente
    @Query(value = "Select count(*)+1 as secuencia "
            + "from proyecto a "
            + "join indicador b ON  b.id_indicador = a.id_indicador "
            + "join metapdot c ON c.id_meta_pdot = b.id_meta_pdot "
            + "join objetivopdot d ON d.id_objetivo_pdot = c.id_objetivo_pdot "
            + "join componente e ON e.id_componente = d.id_componente "
            + "where (e.codigo = :codigo or :codigo is null) "
            + "group by e.id_componente ",
            nativeQuery = true)
    Long SecuenciadelCodigo(String codigo);
}
