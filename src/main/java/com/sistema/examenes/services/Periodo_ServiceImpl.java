package com.sistema.examenes.services;


import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.repository.EjeRepository;
import com.sistema.examenes.repository.PeriodoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class Periodo_ServiceImpl extends GenericServiceImpl<Periodo, Long> implements Periodo_Service {

    @Autowired
    private PeriodoRepository repository;

    @Override
    public CrudRepository<Periodo, Long> getDao() {
        return repository;
    }

    @Override
    public List<Periodo> listar() {
        return repository.listarPeriodo();
    }


}
