package com.sistema.examenes.services;

import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.dto.ReportICPActividades;
import com.sistema.examenes.dto.ReportICProyecto;
import com.sistema.examenes.dto.ReportICompetencia;
import com.sistema.examenes.entity.Competencia;
import com.sistema.examenes.services.generic.GenericService;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface Competencia_Service extends GenericService<Competencia, Long> {
    public List<Competencia> listar();

    public Competencia obtenerCompetenciaId(Long id);

    List<Competencia_DTO> buscarCompetenciasPorNombreDTO(String nombre);

    List<Competencia_DTO> listado();

    List<ReportICompetencia> obtenerReportICompetencias();
    List<ReportICProyecto> obtenerReporteProyectosPorCompetencia(Long competenciaId);
    List<ReportICPActividades> obtenerReporteActividadesPorProyecto(Long proyectoId);
    byte[] exportPdf() throws JRException, FileNotFoundException;
    byte[] exportPdfReportICProyecto(Long competenciaId) throws JRException, FileNotFoundException;
    byte[] exportPdfReportICPActividad(Long proyectoId) throws JRException, FileNotFoundException;
}
