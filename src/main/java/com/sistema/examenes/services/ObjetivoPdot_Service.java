package com.sistema.examenes.services;

import com.sistema.examenes.dto.ObjetivoPdot_DTO;
import com.sistema.examenes.entity.Objetivo_pdot;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ObjetivoPdot_Service extends GenericService<Objetivo_pdot, Long> {
    public List<Objetivo_pdot> listarObjetivosPdots();

    public Objetivo_pdot obtenerObjetivoPdotId(Long id);

    List<ObjetivoPdot_DTO> buscarObjetivosPdotsPorNombre(String nombre);
}
