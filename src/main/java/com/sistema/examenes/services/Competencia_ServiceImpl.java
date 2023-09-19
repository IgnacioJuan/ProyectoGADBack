package com.sistema.examenes.services;

import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.dto.Componente_DTO;
import com.sistema.examenes.dto.ReportICompetencia;
import com.sistema.examenes.entity.Competencia;
import com.sistema.examenes.repository.CompetenciaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import com.sistema.examenes.util.ExampleReportGenerator;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class Competencia_ServiceImpl extends GenericServiceImpl<Competencia, Long> implements Competencia_Service {

    @Autowired
    private CompetenciaRepository repository;

    // Inyectamos el ReportGenerator
    @Autowired
    private ExampleReportGenerator petReportGenerator;

    @Override
    public CrudRepository<Competencia, Long> getDao() {
        return repository;
    }

    @Override
    public List<Competencia> listar() {
        return repository.listarCompetencias();
    }

    @Override
    public Competencia obtenerCompetenciaId(Long id) {
        return repository.obtenerCompetenciaId(id);
    }

    @Override
    public List<Competencia_DTO> buscarCompetenciasPorNombreDTO(String nombre) {
        List<Object[]> resultados = repository.buscarCompetenciasPorNombre(nombre);
        List<Competencia_DTO> competenciasEncontradas = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Competencia_DTO competenciaDTO = new Competencia_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2]);
            competenciasEncontradas.add(competenciaDTO);
        }
        return competenciasEncontradas;
    }

    @Override
    public List<Competencia_DTO> listado() {
        List<Object[]> resultados = repository.listado();
        List<Competencia_DTO> competencias = new ArrayList<>();

        for (Object[] result : resultados) {
            Competencia_DTO dto = new Competencia_DTO();
            dto.setId_competencia(((BigInteger) result[0]).longValue());
            dto.setNombre((String) result[1]);
            dto.setDescripcion((String) result[2]);
            competencias.add(dto);
        }
        return competencias;
    }

    @Override
    public List<ReportICompetencia> obtenerReportICompetencias() {
        List<Object[]> resultados = repository.obtenerReportICompetencias();
        List<ReportICompetencia> reportes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ReportICompetencia reporte = new ReportICompetencia(
                    (String) resultado[1],
                    (double) resultado[2],
                    (double) resultado[3],
                    (double) resultado[4]);
            reportes.add(reporte);
        }
        return reportes;
    }

   /* // Llamamos al ReportGenerator
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return petReportGenerator.exportToPdf(repository.findAll());
    } */
}