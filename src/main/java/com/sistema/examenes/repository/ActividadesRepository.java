package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActividadesRepository extends JpaRepository<Actividades, Long>{

    @Query(value = "SELECT * from actividades where visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<Actividades> listarActividades();
    @Query(value = "SELECT a.id_actividad, a.nombre, a.descripcion, a.presupuesto_referencial, a.recursos_propios, a.codificado, a.devengado " +
            "FROM actividades a " +
            "JOIN aprobacion_actividad aa ON a.id_actividad = aa.id_actividad " +
            "JOIN poa p ON aa.id_poa = p.id_poa " +
            "WHERE a.visible = true AND p.id_poa = :poaId " +
            "ORDER BY a.nombre ASC", nativeQuery = true)
    List<Object[]> listarActividadesPorIdPoa(@Param("poaId") Long poaId);
}
