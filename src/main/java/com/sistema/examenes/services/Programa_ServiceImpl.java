package com.sistema.examenes.services;


import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Programa;
import com.sistema.examenes.repository.ProgramaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import com.sistema.examenes.util.ReportInversionPorCProyecto;
import com.sistema.examenes.util.ReportInversionPorCompetencia;
import com.sistema.examenes.util.ReportInversionPorPProyecto;
import com.sistema.examenes.util.ReportInversionPorPrograma;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class Programa_ServiceImpl extends GenericServiceImpl<Programa, Long> implements Programa_Service {

    @Autowired
    private ProgramaRepository repository;

    @Autowired
    private ReportInversionPorPrograma petReportGenerator;

    @Autowired
    private ReportInversionPorPProyecto ripproyectoReportGenerator;

    @Override
    public CrudRepository<Programa, Long> getDao() {
        return repository;
    }

    @Override
    public List<Programa> listar() {
        return repository.listarProgramas();
    }
    @Override
    public List<Programa_DTO> buscarProgramasPorNombreDTO(String nombre) {
        List<Object[]> resultados = repository.buscarProgramasPorNombre(nombre);
        List<Programa_DTO> programasEncontrados = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Programa_DTO programaDTO = new Programa_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2]
            );
            programasEncontrados.add(programaDTO);
        }
        return programasEncontrados;
    }
    @Override
    public List<ReportIPrograma> obtenerReportIProgramas() {
        List<Object[]> resultados = repository.obtenerReportIProgramas();
        List<ReportIPrograma> reportes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ReportIPrograma reporte = new ReportIPrograma(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (double) resultado[2],
                    (double) resultado[3],
                    (double) resultado[4]);
            reportes.add(reporte);
        }
        return reportes;
    }
    @Override
    public List<ReportIPProyecto> obtenerReporteProyectosPorPrograma(Long programaId) {
        List<Object[]> resultados = repository.obtenerReporteProyectosPorPrograma(programaId);
        List<ReportIPProyecto> reportes = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ReportIPProyecto reporte = new ReportIPProyecto(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2],
                    (Double) resultado[3],
                    (Double) resultado[4],
                    (Double) resultado[5]
            );
            reportes.add(reporte);
        }
        return reportes;
    }

    // Llamamos al ReportGenerator
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        List<Object[]> resultados = repository.obtenerReportIProgramas();
        List<ReportIPrograma> reportes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ReportIPrograma reporte = new ReportIPrograma(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (double) resultado[2],
                    (double) resultado[3],
                    (double) resultado[4]);
            reportes.add(reporte);
        }
        return petReportGenerator.exportToPdf(reportes);
    }

    @Override
    public byte[] exportPdfReportIPProyecto(Long programaId) throws JRException, FileNotFoundException {
        List<Object[]> resultados = repository.obtenerReporteProyectosPorPrograma(programaId);
        List<ReportIPProyecto> reportes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ReportIPProyecto reporte = new ReportIPProyecto(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2],
                    (Double) resultado[3],
                    (Double) resultado[4],
                    (Double) resultado[5]);
            reportes.add(reporte);
        }
        return ripproyectoReportGenerator.exportToPdf(reportes);
    }
}
