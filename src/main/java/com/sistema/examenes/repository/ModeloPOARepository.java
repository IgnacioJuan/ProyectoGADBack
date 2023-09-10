package com.sistema.examenes.repository;


import com.sistema.examenes.entity.ModeloPOA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ModeloPOARepository extends JpaRepository<ModeloPOA, Long> {
    @Query(value = "SELECT * FROM modelopoa WHERE visible = true ORDER BY CASE WHEN estado = 'ACTIVO' THEN 0 ELSE 1 END, fecha_inicial DESC", nativeQuery = true)
    List<ModeloPOA> listarModelosPOA();


    @Modifying
    @Transactional
    @Query(value = "UPDATE modelopoa SET estado = 'INACTIVO' WHERE id_modelo_poa <> :id_modelo", nativeQuery = true)
    int inhabilitarModelos(Long id_modelo);
}
