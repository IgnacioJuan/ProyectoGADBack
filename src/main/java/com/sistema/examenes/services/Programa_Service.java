package com.sistema.examenes.services;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Programa;
import com.sistema.examenes.services.generic.GenericService;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface Programa_Service extends GenericService<Programa, Long> {
    public List<Programa> listar();
    List<Programa_DTO> buscarProgramasPorNombreDTO(String nombre);
    List<ReportIPrograma> obtenerReportIProgramas();
    List<ReportIPProyecto> obtenerReporteProyectosPorPrograma(Long programaId);
    byte[] exportPdf() throws JRException, FileNotFoundException;
    byte[] exportPdfReportIPProyecto(Long competenciaId) throws JRException, FileNotFoundException;
}
