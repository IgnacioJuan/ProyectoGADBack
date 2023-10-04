package com.sistema.examenes.repository;


import com.sistema.examenes.entity.ObjetivoPND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetivoPNDRepository extends JpaRepository<ObjetivoPND, Long> {
    @Query(value = "SELECT * from objetivopnd where visible =true ORDER BY nombre ASC", nativeQuery = true)
    List<ObjetivoPND> listarObjetivosPNDS();
    @Query(value = "SELECT id_objetivo_pnd, nombre, descripcion, id_eje, e.nombre FROM objetivopnd o JOIN eje e ON o.id_eje = e.id_eje WHERE LOWER(o.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND o.visible = true", nativeQuery = true)
    List<Object[]> buscarObjetivosPNDSPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT o.id_objetivo_pnd, o.nombre, o.descripcion, o.id_eje, e.nombre FROM objetivopnd o JOIN eje e ON o.id_eje = e.id_eje WHERE o.id_eje = :idEje AND o.visible = true ORDER BY o.nombre ASC", nativeQuery = true)
    List<Object[]> listarObjetivosPorIdEje(@Param("idEje") Long idEje);
    @Query(value = "SELECT o FROM ObjetivoPND o WHERE o.eje.id_eje = :idEje AND o.visible = true")
    List<ObjetivoPND> listarObjetivosPorIdEjex(@Param("idEje") Long idEje);

    @Query(value = "SELECT id_objetivo_pnd, nombre FROM objetivopnd WHERE id_eje = :idEje AND visible = true ORDER BY nombre ASC", nativeQuery = true)
    List<Object[]> listarObjetivosPorEjeNEW(@Param("idEje") Long idEje);
}
