package com.sistema.examenes.services;

import com.sistema.examenes.dto.Componente_DTO;
import com.sistema.examenes.entity.Componente;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Componente_Service extends GenericService<Componente, Long> {
    public List<Componente> listar();

    public Componente obtenerComponenteId(Long id);

    List<Componente_DTO> buscarComponentesPorNombre(String nombre);
}
