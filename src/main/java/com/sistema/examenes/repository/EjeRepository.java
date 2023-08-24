package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Eje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjeRepository extends JpaRepository<Eje, Long> {
    @Query(value = "SELECT * from eje where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Eje> listarEje();

    @Query(value = "SELECT id_eje, nombre FROM eje WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarEjesPorNombre(@Param("nombre") String nombre);

}
