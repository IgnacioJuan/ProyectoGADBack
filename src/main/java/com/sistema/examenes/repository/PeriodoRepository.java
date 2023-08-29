package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.entity.Poa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    @Query(value = "SELECT * from periodo where visible =true", nativeQuery = true)
    List<Periodo> listarPeriodo();

}
