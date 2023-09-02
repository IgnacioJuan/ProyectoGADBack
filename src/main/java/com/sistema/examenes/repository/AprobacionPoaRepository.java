package com.sistema.examenes.repository;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AprobacionPoaRepository extends JpaRepository<AprobacionPoa, Long>{

    @Query(value = "SELECT * from aprobacion_poa where visible = true", nativeQuery = true)
    List<AprobacionPoa> listarAprobacionPoa();

    @Query(value =
            "SELECT " +
                    "p.id_poa, " +
                    "per.primer_nombre || ' ' || per.primer_apellido as responsable, " +
                    "pr.nombre as nombre_proyecto, " +
                    "pr.fecha_inicio, " +
                    "pr.fecha_fin, " +
                    "os.nombre as nombre_ods, " +
                    "op.nombre as nombre_pnd, " +
                    "opt.nombre as nombre_pdot, " +
                    "pr.objetivo as objetivo_proyecto, " +
                    "ind.nombre as nombre_indicador, " +
                    "pr.meta as meta_proyecto, " +
                    "p.linea_base, " +
                    "p.cobertura, " +
                    "p.localizacion, " +
                    "p.barrio, " +
                    "p.comunidad, " +
                    "p.tipo_periodo " +
                    "FROM aprobacion_poa aa " +
                    "JOIN poa p ON aa.id_poa = p.id_poa " +
                    "JOIN proyecto pr ON aa.id_proyecto = pr.id_proyecto " +
                    "JOIN objetivoods os ON pr.id_ods = os.id_objetivo_ods " +
                    "JOIN objetivopnd op ON pr.id_pnd = op.id_objetivo_pnd " +
                    "JOIN indicador ind ON pr.id_indicador = ind.id_indicador " +
                    "JOIN metapdot mtp ON ind.id_meta_pdot = mtp.id_meta_pdot " +
                    "JOIN objetivopdot opt ON mtp.id_objetivo_pdot = opt.id_objetivo_pdot " +
                    "JOIN usuarios u ON p.id_responsable = u.id " +
                    "JOIN persona per ON u.persona_id_persona = per.id_persona " +
                    "WHERE aa.visible = true", nativeQuery = true)
    List<Object[]> obtenerAprobacionesPoa();
    @Query(value =
            "SELECT " +
                    "p.id_poa, " +
                    "per.primer_nombre || ' ' || per.primer_apellido as responsable, " +
                    "pr.nombre as nombre_proyecto, " +
                    "pr.fecha_inicio, " +
                    "pr.fecha_fin, " +
                    "os.nombre as nombre_ods, " +
                    "op.nombre as nombre_pnd, " +
                    "opt.nombre as nombre_pdot, " +
                    "pr.objetivo as objetivo_proyecto, " +
                    "ind.nombre as nombre_indicador, " +
                    "pr.meta as meta_proyecto, " +
                    "p.linea_base, " +
                    "p.cobertura, " +
                    "p.localizacion, " +
                    "p.barrio, " +
                    "p.comunidad, " +
                    "p.tipo_periodo " +
                    "FROM aprobacion_poa aa " +
                    "JOIN poa p ON aa.id_poa = p.id_poa " +
                    "JOIN proyecto pr ON aa.id_proyecto = pr.id_proyecto " +
                    "JOIN objetivoods os ON pr.id_ods = os.id_objetivo_ods " +
                    "JOIN objetivopnd op ON pr.id_pnd = op.id_objetivo_pnd " +
                    "JOIN indicador ind ON pr.id_indicador = ind.id_indicador " +
                    "JOIN metapdot mtp ON ind.id_meta_pdot = mtp.id_meta_pdot " +
                    "JOIN objetivopdot opt ON mtp.id_objetivo_pdot = opt.id_objetivo_pdot " +
                    "JOIN usuarios u ON p.id_responsable = u.id " +
                    "JOIN persona per ON u.persona_id_persona = per.id_persona " +
                    "WHERE aa.visible = true and p.id_poa= :idPoa", nativeQuery = true)
    List<Object[]> obtenerAprobacionPoaPorId(@Param("idPoa") BigInteger id_poa);

    @Query("SELECT a FROM AprobacionPoa a WHERE a.poa.id = :idPoa")
    AprobacionPoa findByPoaId(@Param("idPoa") Long idPoa);

    @Query(value = "SELECT id_aprobacionpoa, estado, observacion\n" +
            "FROM  aprobacion_poa\n" +
            "WHERE id_poa  = :idPoa  AND  visible=true;", nativeQuery = true)
    List<Object[]> listarAprobacionPoaPorIdPoa(@Param("idPoa") Long idPoa);

}
