package com.sistema.examenes.services;


import com.sistema.examenes.dto.Indicador_DTO;
import com.sistema.examenes.dto.MetaPdot_DTO;
import com.sistema.examenes.entity.Indicador;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Indicador_Service extends GenericService<Indicador, Long> {
    public List<Indicador> listar();

    public Indicador obtenerIndicadorId(Long id);
    List<Indicador_DTO> buscarIndicadoresPorNombre(String nombre);

    List<Indicador_DTO> listarIndicadoresPorIdMetaPdot(Long idMetaPdot);
}
