package com.sistema.examenes.services;


import com.sistema.examenes.dto.MetaPdot_DTO;
import com.sistema.examenes.entity.MetaPDOT;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface MetaPDOT_Service extends GenericService<MetaPDOT, Long> {
    public List<MetaPDOT> listar();

    public MetaPDOT obtenerMetaPdotId(Long id);
    List<MetaPdot_DTO> buscarMetasPdotsPorNombre(String nombre);
}
