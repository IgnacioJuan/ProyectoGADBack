package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Objetivo_pdot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetivoPdotRepository extends JpaRepository<Objetivo_pdot, Long> {
    @Query(value = "SELECT * from objetivopdot where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Objetivo_pdot> listarObjetivosPdots();

    @Query(value = "SELECT * FROM objetivopdot WHERE id_objetivo_pdot= :id AND visible = true", nativeQuery = true)
    Objetivo_pdot obtenerObjetivoPdotId(@Param("id") Long id);

    @Query(value = "SELECT id_objetivo_pdot, nombre, descripcion FROM objetivopdot WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarObjetivosPdotsPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT op.id_objetivo_pdot, op.nombre, op.descripcion FROM objetivopdot op INNER JOIN componente c ON op.id_componente = c.id_componente WHERE c.id_componente = :idComponente AND op.visible = true ORDER BY op.nombre ASC", nativeQuery = true)
    List<Object[]> listarObjetivosPdotsDTOPorIdComponente(@Param("idComponente") Long idComponente);

}
