package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.ReformaTraspaso_D;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReformaTraspaso_DRepository extends JpaRepository<ReformaTraspaso_D, Long>{

    @Query(value = "SELECT * from reforma_traspasoD where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<ReformaTraspaso_D> listarReformasTraspaso_D();
}
