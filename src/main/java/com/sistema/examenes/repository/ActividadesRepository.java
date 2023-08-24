package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActividadesRepository extends JpaRepository<Actividades, Long>{

    @Query(value = "SELECT * from actividades where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<Actividades> listarActividades();
}
