package com.sistema.examenes.services;

import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.entity.Competencia;
import com.sistema.examenes.services.generic.GenericService;
import java.util.List;

public interface Competencia_Service extends GenericService<Competencia, Long> {
    public List<Competencia> listar();

    public Competencia obtenerCompetenciaId(Long id);
    List<Competencia_DTO> buscarCompetenciasPorNombreDTO(String nombre);

}
