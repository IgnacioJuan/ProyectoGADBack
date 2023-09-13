package com.sistema.examenes.services;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.AprobacionPoa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface AprobacionPoaService extends GenericService<AprobacionPoa, Long> {

    public List<AprobacionPoa> listarAprobacionPoa();
    AprobacionPoa obtenerAprobacionPorIdPoa(Long idPoa);
    List<AprobacionPoa_DTO> listarAprobacionPoaPorIdPoa(Long idPoa);

         /******* MODULO APROBACION POA ********/
    List<AprobPoa_DTO> obtenerPoasCompletos();
    AprobPoa_DTO obtenerPoaCompletoPorId(Long idPoa);

}
