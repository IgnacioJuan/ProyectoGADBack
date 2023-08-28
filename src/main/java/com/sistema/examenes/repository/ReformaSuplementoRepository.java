package com.sistema.examenes.repository;

import com.sistema.examenes.entity.ReformaSuplemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReformaSuplementoRepository extends JpaRepository<ReformaSuplemento, Long>{

    @Query(value = "SELECT * from reforma_suplemento where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<ReformaSuplemento> listarReformaSuplemento();
}
