package com.sistema.examenes.services;

import com.sistema.examenes.dto.Poa_DTO;
import com.sistema.examenes.dto.ProyectoResumenDTO;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Poa_Service extends GenericService<Poa, Long> {
    public List<Poa> listar();

    public Poa obtenerPoaId(Long id);

    public List<Poa> listarPoadelProyectoconEstado(Long id_proyecto, String estado);

    public List<Poa_DTO> listarPoasDeModelo();
}
