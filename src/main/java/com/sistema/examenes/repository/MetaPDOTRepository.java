package com.sistema.examenes.repository;


import com.sistema.examenes.entity.MetaPDOT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetaPDOTRepository extends JpaRepository<MetaPDOT, Long> {
    @Query(value = "SELECT * from metapdot where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<MetaPDOT> listarMetaPDOTs();

    @Query(value = "SELECT * FROM metapdot WHERE id_meta_pdot= :id AND visible = true", nativeQuery = true)
    MetaPDOT obtenerMetaPdotId(@Param("id") Long id);
    @Query(value = "SELECT id_meta_pdot, nombre, descripcion, porcentaje_meta FROM metapdot WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarMetasPdotsPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT m.id_meta_pdot, m.nombre, m.descripcion, m.porcentaje_meta FROM metapdot m INNER JOIN objetivopdot o ON m.id_objetivo_pdot = o.id_objetivo_pdot WHERE o.id_objetivo_pdot = :idObjetivoP AND m.visible = true ORDER BY m.nombre ASC", nativeQuery = true)
    List<Object[]> listarMetasPdotsDTOPorIdObjPdot(@Param("idObjetivoP") Long idObjetivoP);

}
