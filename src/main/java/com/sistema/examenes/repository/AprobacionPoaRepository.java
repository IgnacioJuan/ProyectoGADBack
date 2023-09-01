package com.sistema.examenes.repository;

import com.sistema.examenes.entity.AprobacionPoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprobacionPoaRepository extends JpaRepository<AprobacionPoa, Long>{

    @Query(value = "SELECT * from aprobacion_poa where visible = true", nativeQuery = true)
    List<AprobacionPoa> listarAprobacionPoa();
}
