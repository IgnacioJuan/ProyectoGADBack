package com.sistema.examenes.services;

import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Poa_Service extends GenericService<Poa, Long> {
    public List<Poa> listar();

    public Poa obtenerPoaId(Long id);
}
