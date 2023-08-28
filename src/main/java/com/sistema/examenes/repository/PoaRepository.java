package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Poa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoaRepository extends JpaRepository<Poa, Long> {
    @Query(value = "SELECT * from poa where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<Poa> listarPoas();

    @Query(value = "SELECT * FROM poa WHERE id_poa= :id AND visible = true", nativeQuery = true)
    Poa obtenerPoaId(@Param("id") Long id);

    @Query(value = "SELECT id_poa, meta_alcanzar, meta_fisica, avance_real, fecha_inicio, fecha_fin, localizacion, cobertura, barrio, comunidad, nombre_funcionario, cargo, recursos_propios, transferencias_gobierno, convenios, linea_base FROM poa WHERE estado = 'aprobado' AND visible = true", nativeQuery = true)
    List<Object[]> listarPoasAprobados();

}
