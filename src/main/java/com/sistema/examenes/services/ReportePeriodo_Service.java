package com.sistema.examenes.services;



import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ReportePeriodo_Service extends GenericService<ReportePeriodo, Long> {
    public List<ReportePeriodo> listar();

}
