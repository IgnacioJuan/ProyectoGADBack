package com.sistema.examenes.services;

import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface AprobacionPoaService extends GenericService<AprobacionPoa, Long> {

    public List<AprobacionPoa> listarAprobacionPoa();
}
