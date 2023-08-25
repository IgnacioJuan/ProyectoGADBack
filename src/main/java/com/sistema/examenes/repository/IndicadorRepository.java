package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
    @Query(value = "SELECT * from indicador where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Indicador> listarIndicadores();

    @Query(value = "SELECT * FROM indicador WHERE id_indicador= :id AND visible = true", nativeQuery = true)
    Indicador obtenerIndicadorId(@Param("id") Long id);
    @Query(value = "SELECT id_indicador, nombre, descripcion, tipo_evaluacion  FROM indicador WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarIndicadoresPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT i.id_indicador, i.nombre, i.descripcion, i.tipo_evaluacion FROM indicador i INNER JOIN metapdot m ON i.id_meta_pdot = m.id_meta_pdot WHERE m.id_meta_pdot = :idMetaPdot AND i.visible = true ORDER BY i.nombre ASC", nativeQuery = true)
    List<Object[]> listarIndicadoresPorIdMetaPdot(@Param("idMetaPdot") Long idMetaPdot);
}
