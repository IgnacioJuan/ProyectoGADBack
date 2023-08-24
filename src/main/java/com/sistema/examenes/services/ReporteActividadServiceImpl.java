package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReporteActividad;
import com.sistema.examenes.repository.ReporteActividadRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteActividadServiceImpl extends GenericServiceImpl<ReporteActividad, Long> implements ReporteActividadService{
    @Autowired
    ReporteActividadRepository reporteActividadRepository;
    @Override
    public CrudRepository<ReporteActividad, Long> getDao() {
        return reporteActividadRepository;
    }

    @Override
    public List<ReporteActividad> listarReporteActividades() {
        return reporteActividadRepository.listarReporteActividades();
    }
}
