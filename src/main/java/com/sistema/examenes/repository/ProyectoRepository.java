package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    @Query(value = "SELECT * from proyecto where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Proyecto> listarProyectos();
    @Query(value = "SELECT * from proyecto where visible =true ORDER BY nombre ASC and id_modelo_poa = :id_modelo_poa", nativeQuery = true)
    List<Proyecto> listarProyectosdelModelo(Long id_modelo_poa);
    @Query(value = "SELECT " +
            "p.id_proyecto, p.codigo, p.nombre, p.objetivo, p.meta, " +
            "p.porcentaje_alcance, p.fecha_inicio, " +
            "pnd.id_objetivo_pnd AS id_objetivo_pnd, pnd.nombre AS nombre_objetivopnd, " +
            "ods.id_objetivo_ods AS id_objetivo_ods, ods.nombre AS nombre_objetivoods, " +
            "mp.id_modelo_poa AS id_modelo_poa, mp.nombre AS nombre_modelopoa, " +
            "prog.id_programa AS id_programa, prog.nombre AS nombre_programa, " +
            "ind.id_indicador AS id_indicador, ind.nombre AS nombre_indicador, " +
            "meta.id_meta_pdot AS id_meta_pdot, meta.nombre AS nombre_meta_pdot, " +
            "opd.id_objetivo_pdot AS id_objetivo_pdto, opd.nombre AS nombre_objetivopdto, " +
            "compe.id_competencia AS id_competencia, compe.nombre AS nombre_competencia, " +
            "comp.id_componente AS id_componente, comp.nombre AS nombre_componente " +
            "FROM proyecto p " +
            "JOIN objetivopnd pnd ON p.id_pnd = pnd.id_objetivo_pnd " +
            "JOIN objetivoods ods ON p.id_ods = ods.id_objetivo_ods " +
            "JOIN modelopoa mp ON p.id_modelo_poa = mp.id_modelo_poa " +
            "JOIN programa prog ON p.id_programa = prog.id_programa " +
            "JOIN competencia compe ON p.id_competencia = compe.id_competencia " +
            "JOIN indicador ind ON p.id_indicador = ind.id_indicador " +
            "LEFT JOIN metapdot meta ON ind.id_meta_pdot = meta.id_meta_pdot " +
            "LEFT JOIN objetivopdot opd ON meta.id_objetivo_pdot = opd.id_objetivo_pdot " +
            "LEFT JOIN componente comp ON opd.id_componente = comp.id_componente " +
            "WHERE p.visible = true " +
            "ORDER BY p.nombre ASC", nativeQuery = true)
    List<Object[]> listarProyectosConRelaciones();
}
