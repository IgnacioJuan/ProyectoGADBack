package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReporteActividad;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ReporteActividadService extends GenericService<ReporteActividad, Long> {

    public List<ReporteActividad> listarReporteActividades();
}
