package com.sistema.examenes.services;

import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface PresupuestoExternoService extends GenericService<PresupuestoExterno, Long> {
    public List<PresupuestoExterno> listarPresupuestoExterno();
}
