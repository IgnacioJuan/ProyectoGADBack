package com.sistema.examenes.repository;

import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    @Query(value = "SELECT * from proyecto where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Proyecto> listarProyectos();
    @Query(value = "SELECT * from proyecto where visible =true and id_modelo_poa = :id_modelo_poa ORDER BY nombre ASC ", nativeQuery = true)
    List<Proyecto> listarProyectosdelModelo(Long id_modelo_poa);

    @Query(value = "SELECT p.id_proyecto,p.codigo,p.meta,p.nombre FROM public.proyecto p join public.modelopoa ON modelopoa.id_modelo_poa = p.id_modelo_poa  WHERE modelopoa.estado='ACTIVO' AND p.visible=true ORDER BY p.nombre", nativeQuery = true)
    List<Object[]> listProjectsActives();

    //create query for this query SELECT p.id_proyecto,p.codigo,p.meta,p.nombre FROM public.proyecto p join public.modelopoa ON modelopoa.id_modelo_poa = p.id_modelo_poa
    //WHERE modelopoa.estado='ACTIVO' AND p.visible=true ORDER BY p.nombre;
    @Query(value = "SELECT p.id_proyecto,p.codigo,p.meta,p.nombre FROM public.proyecto p join public.modelopoa ON modelopoa.id_modelo_poa = p.id_modelo_poa  WHERE modelopoa.estado='ACTIVO' AND p.visible=true ORDER BY p.nombre", nativeQuery = true)
    List<Object[]> listActiveProjects();

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
    @Query(value = "Select count(*)+1 as secuencia " +
            "from proyecto a " +
            "join indicador b ON  b.id_indicador = a.id_indicador " +
            "join metapdot c ON c.id_meta_pdot = b.id_meta_pdot " +
            "join objetivopdot d ON d.id_objetivo_pdot = c.id_objetivo_pdot " +
            "join componente e ON e.id_componente = d.id_componente " +
            "where (e.codigo = :codigo or :codigo is null) " +
            "group by e.id_componente "
            , nativeQuery = true)
    Long SecuenciadelCodigo(String codigo);
}