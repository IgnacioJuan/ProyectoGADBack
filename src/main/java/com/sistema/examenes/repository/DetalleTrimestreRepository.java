package com.sistema.examenes.repository;

import com.sistema.examenes.entity.DetalleTrimestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleTrimestreRepository extends JpaRepository<DetalleTrimestre, Long> {

    @Query(value = "SELECT * from detalle_trimestre where visible=true ORDER BY id_detalle_trimestre ASC", nativeQuery = true)
    List<DetalleTrimestre> listarDetalleTrimestres();
}
