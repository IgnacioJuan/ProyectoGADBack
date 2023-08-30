package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    @Query(value = "SELECT * from competencia where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Competencia> listarCompetencias();

    @Query(value = "SELECT * FROM competencia WHERE id_competencia= :id AND visible = true", nativeQuery = true)
    Competencia obtenerCompetenciaId(@Param("id") Long id);
    @Query(value = "SELECT id_competencia, nombre, descripcion FROM competencia WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarCompetenciasPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT id_competencia, nombre, descripcion FROM competencia WHERE visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<Object[]> listado();
}