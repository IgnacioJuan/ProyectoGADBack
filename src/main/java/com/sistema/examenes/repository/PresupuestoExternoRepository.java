package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.entity.ReformaTraspaso_I;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PresupuestoExternoRepository extends JpaRepository<PresupuestoExterno, Long> {

    @Query(value = "SELECT * from presupuesto_externo where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<PresupuestoExterno> listarPresupuestoExterno();
}
