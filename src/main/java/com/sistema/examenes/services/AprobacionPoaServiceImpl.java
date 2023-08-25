package com.sistema.examenes.services;

import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.repository.AprobacionPoaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AprobacionPoaServiceImpl extends GenericServiceImpl<AprobacionPoa, Long> implements AprobacionPoaService{

    @Autowired
    AprobacionPoaRepository AprobacionPoaRepository;
    @Override
    public CrudRepository<AprobacionPoa, Long> getDao() {
        return AprobacionPoaRepository;
    }

    @Override
    public List<AprobacionPoa> listarAprobacionPoa() {
        return AprobacionPoaRepository.listarAprobacionPoa();
    }
}
