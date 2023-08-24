package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReporteTrimestre;
import com.sistema.examenes.repository.ReporteTrimestreRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteTrimestreServiceImpl extends GenericServiceImpl<ReporteTrimestre, Long> implements ReporteTrimestreService{

    @Autowired
    ReporteTrimestreRepository reporteTrimestreRepository;
    @Override
    public CrudRepository<ReporteTrimestre, Long> getDao() {
        return reporteTrimestreRepository;
    }

    @Override
    public List<ReporteTrimestre> listarReporteTrimestres() {
        return reporteTrimestreRepository.listarReporteTrimestres();
    }
}
