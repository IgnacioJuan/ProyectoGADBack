package com.sistema.examenes.services;

import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.dto.ProjectsActivesDto;
import com.sistema.examenes.dto.ProyectoExportarexcelDTO;
import com.sistema.examenes.dto.reportePresupuestoDTO;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.services.generic.GenericService;
import java.io.FileNotFoundException;

import java.util.List;
import net.sf.jasperreports.engine.JRException;

public interface Proyecto_Service extends GenericService<Proyecto, Long> {

    public List<Proyecto> listar();

    public List<Proyecto> listardelModelo(Long id_modelo_poa);

    public List<ProyectoExportarexcelDTO> exportarexcel(Long id_modelo_poa);

    List<Proyecto> findByIds(List<Long> ids);
   
   List<reportePresupuestoDTO> obtenerReportePresupuesto();

    List<ProjectsActivesDto> listActiveProjects(Long id_usuario);

    List<ProjectByIdDto> ProjectById(Long id_proyecto);

    Long secuenciaproyecto(String codigo);
    
    byte[] exportPdfMETAS() throws JRException, FileNotFoundException;

}
