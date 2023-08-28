package com.sistema.examenes.repository;


import com.sistema.examenes.entity.ModeloPOA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeloPOARepository extends JpaRepository<ModeloPOA, Long> {
    @Query(value = "SELECT * from modelopoa where visible =true ORDER BY fecha_inicial desc", nativeQuery = true)
    List<ModeloPOA> listarModelosPOA();
}
