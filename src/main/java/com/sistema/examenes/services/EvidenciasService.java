package com.sistema.examenes.services;

import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface EvidenciasService extends GenericService<Evidencias, Long> {

    public List<Evidencias> listarEvidencias();
}
