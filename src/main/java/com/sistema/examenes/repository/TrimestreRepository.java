package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Trimestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrimestreRepository extends JpaRepository<Trimestre, Long> {

    @Query(value = "SELECT * from trimestre where visible=true ORDER BY nombre ASC", nativeQuery = true)
    List<Trimestre> listarTrimestres();
}
