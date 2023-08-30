package com.sistema.examenes.services;

import com.sistema.examenes.dto.ProyectoResumenDTO;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Proyecto_Service extends GenericService<Proyecto, Long> {
    public List<Proyecto> listar();
    public List<Proyecto> listardelModelo(Long id_modelo_poa);


    public List<ProyectoResumenDTO> listarProyectosConRelaciones();
}
