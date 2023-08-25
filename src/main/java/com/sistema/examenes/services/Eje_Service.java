package com.sistema.examenes.services;



import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Eje_Service extends GenericService<Eje, Long> {
    public List<Eje> listar();


    List<Eje_DTO> buscarEjesPorNombreDTO(String nombre);

}
