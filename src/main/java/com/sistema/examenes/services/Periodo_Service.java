package com.sistema.examenes.services;



import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Periodo_Service extends GenericService<Periodo, Long> {
    public List<Periodo> listar();

}
