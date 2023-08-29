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

    @Query(value = "SELECT p.* " +
            "from poa p " +
            "join aprobacion_poa ap " +
            "on ap.id_poa = p.id_poa " +
            "where p.visible =true " +
            "and ap.id_proyecto = :id_proyecto " +
            "and (ap.estado= :estado or :estado is null)" +
            "ORDER BY fecha_inicio desc", nativeQuery = true)
    List<Poa> listarPoadelProyectoconEstado(Long id_proyecto, String estado);
    
    @Query(value = "SELECT id_poa, fecha_inicio, fecha_fin, localizacion, cobertura, barrio, comunidad, nombre_funcionario, cargo, linea_base, tipo_periodo FROM poa WHERE estado = 'aprobado' AND visible = true", nativeQuery = true)
    List<Object[]> listarPoasAprobados();

}
