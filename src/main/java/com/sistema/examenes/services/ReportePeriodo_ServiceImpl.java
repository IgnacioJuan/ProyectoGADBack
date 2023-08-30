package com.sistema.examenes.services;


import com.sistema.examenes.repository.ReportePeriodoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportePeriodo_ServiceImpl extends GenericServiceImpl<ReportePeriodo, Long> implements ReportePeriodo_Service {

    @Autowired
    private ReportePeriodoRepository repository;

    @Override
    public CrudRepository<ReportePeriodo, Long> getDao() {
        return repository;
    }

    @Override
    public List<ReportePeriodo> listar() {
        return repository.listarReportePeriodo();
    }

}
