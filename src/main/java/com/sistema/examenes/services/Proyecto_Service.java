package com.sistema.examenes.services;

import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.dto.ProjectsActivesDto;
import com.sistema.examenes.dto.reportePresupuestoDTO;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Proyecto_Service extends GenericService<Proyecto, Long> {
    public List<Proyecto> listar();
    public List<Proyecto> listardelModelo(Long id_modelo_poa);
    List<Proyecto> findByIds(List<Long> ids);
   
   List<reportePresupuestoDTO> obtenerReportePresupuesto();

    List<ProjectsActivesDto> listActiveProjects(Long id_usuario);

    List<ProjectByIdDto> ProjectById(Long id_proyecto);
    Long secuenciaproyecto(String codigo);
}