package com.sistema.examenes.services;

import com.sistema.examenes.dto.ObjetivoPnd_DTO;
import com.sistema.examenes.entity.ObjetivoPND;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ObjetivoPND_Service extends GenericService<ObjetivoPND, Long> {
    public List<ObjetivoPND> listar();
    List<ObjetivoPnd_DTO> buscarObjetivosPNDSPorNombreDTO(String nombre);

    List<ObjetivoPnd_DTO> listarObjetivosPorIdEjeDTO(Long idEje);
    List<ObjetivoPND> listarObjetivosPorIdEjex(Long idEje);

}
