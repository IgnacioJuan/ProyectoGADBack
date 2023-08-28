package com.sistema.examenes.services;

import com.sistema.examenes.dto.ObjetivoOds_DTO;
import com.sistema.examenes.entity.ObjetivoODS;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ObjetivoODS_Service extends GenericService<ObjetivoODS, Long> {
    
    public List<ObjetivoODS> listar();
    
    List<ObjetivoOds_DTO> buscarObjetivosODSPorNombreDTO(String nombre);

}
