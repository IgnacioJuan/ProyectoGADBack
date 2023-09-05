package com.sistema.examenes.repository;


import com.sistema.examenes.entity.ModeloPOA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ModeloPOARepository extends JpaRepository<ModeloPOA, Long> {
    @Query(value = "SELECT * from modelopoa where visible =true ORDER BY fecha_inicial desc", nativeQuery = true)
    List<ModeloPOA> listarModelosPOA();

    @Modifying
    @Transactional
    @Query(value = "UPDATE modelopoa SET estado = 'INACTIVO' WHERE id_modelo_poa <> :id_modelo", nativeQuery = true)
    int inhabilitarModelos(Long id_modelo);
}
