package com.sistema.examenes.services;

import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.repository.PresupuestoExternoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoExternoServiceImpl extends GenericServiceImpl<PresupuestoExterno, Long> implements PresupuestoExternoService {
    @Autowired
    PresupuestoExternoRepository PresupuestoExternoRepository;
    @Override
    public CrudRepository<PresupuestoExterno, Long> getDao() {
        return PresupuestoExternoRepository;
    }

    @Override
    public List<PresupuestoExterno> listarPresupuestoExterno() {
        return PresupuestoExternoRepository.listarPresupuestoExterno();
    }
}
