package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    @Query(value = "SELECT * from programa where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Programa> listarProgramas();

    @Query(value = "SELECT id_programa, nombre, descripcion FROM programa WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarProgramasPorNombre(@Param("nombre") String nombre);

}
