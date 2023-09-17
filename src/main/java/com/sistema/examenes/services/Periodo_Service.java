package com.sistema.examenes.services;



import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.dto.PeriodoTotalPOA_DTO;
import com.sistema.examenes.dto.Periodo_DTO;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.services.generic.GenericService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Periodo_Service extends GenericService<Periodo, Long> {
    public List<Periodo> listar();  
    List<Periodo_DTO> obtenerPorcentajeYReferenciaPorPoa(Long id_poa);
    PeriodoTotalPOA_DTO obtenerTotalesPorPoa(Long idPoa);

    List<Periodo>listarPeriodosPorActividad(Long actividadId);

    ResponseEntity<Void> eliminarPeriodosPorActividad(Long actividadId);
}
