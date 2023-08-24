package com.sistema.examenes.services;

import com.sistema.examenes.dto.Programa_DTO;
import com.sistema.examenes.entity.Programa;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Programa_Service extends GenericService<Programa, Long> {
    public List<Programa> listar();
    List<Programa_DTO> buscarProgramasPorNombreDTO(String nombre);

}
