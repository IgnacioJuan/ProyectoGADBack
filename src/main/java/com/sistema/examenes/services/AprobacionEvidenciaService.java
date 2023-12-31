package com.sistema.examenes.services;


import com.sistema.examenes.dto.AprobacionEvidenciaDTO;
import com.sistema.examenes.dto.ObjetivoPdot_DTO;
import com.sistema.examenes.entity.AprobacionEvidencia;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface AprobacionEvidenciaService  extends GenericService<AprobacionEvidencia, Long> {
    public List<AprobacionEvidencia> listarAprobacionEvidencia();
    List<AprobacionEvidenciaDTO> listarAprobacionEvidenciaPorIdArchivo(Long archivoId);
}
