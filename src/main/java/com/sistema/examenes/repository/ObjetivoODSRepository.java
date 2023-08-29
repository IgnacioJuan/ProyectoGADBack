package com.sistema.examenes.repository;


import com.sistema.examenes.entity.ObjetivoODS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetivoODSRepository extends JpaRepository<ObjetivoODS, Long> {

    @Query(value = "SELECT * from objetivoods where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<ObjetivoODS> listarObjetivosODS();
    @Query(value = "SELECT id_objetivo_ods, nombre, descripcion FROM objetivo_ods WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND visible = true", nativeQuery = true)
    List<Object[]> buscarObjetivosODSPorNombre(@Param("nombre") String nombre);

}
