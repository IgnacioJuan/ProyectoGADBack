package com.sistema.examenes.repository;

import com.sistema.examenes.entity.ReformaTraspaso_I;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReformaTraspaso_IRepository extends JpaRepository<ReformaTraspaso_I, Long>{

    @Query(value = "SELECT * from reforma_traspasoI where visible = true ORDER BY fecha DESC", nativeQuery = true)
    List<ReformaTraspaso_I> listarReformasTraspaso_I();
}
