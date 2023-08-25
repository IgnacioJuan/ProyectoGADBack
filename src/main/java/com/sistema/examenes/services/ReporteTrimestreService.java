package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReporteTrimestre;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ReporteTrimestreService extends GenericService<ReporteTrimestre, Long> {

    public List<ReporteTrimestre> listarReporteTrimestres();
}
