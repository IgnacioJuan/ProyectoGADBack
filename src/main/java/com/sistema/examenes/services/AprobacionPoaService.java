package com.sistema.examenes.services;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.AprobacionPoa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.services.generic.GenericService;

import java.math.BigInteger;
import java.util.List;

public interface AprobacionPoaService extends GenericService<AprobacionPoa, Long> {

    public List<AprobacionPoa> listarAprobacionPoa();

    List<AprobPoa_DTO> obtenerAprobacionesPoa();
    AprobPoa_DTO obtenerAprobacionPoaPorId(BigInteger idPoa);

    AprobacionPoa obtenerAprobacionPorIdPoa(Long idPoa);

    List<AprobacionPoa_DTO> listarAprobacionPoaPorIdPoa(Long idPoa);
}
