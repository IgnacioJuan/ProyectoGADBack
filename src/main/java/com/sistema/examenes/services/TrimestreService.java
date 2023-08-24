package com.sistema.examenes.services;

import com.sistema.examenes.entity.Trimestre;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface TrimestreService extends GenericService<Trimestre, Long> {

    public List<Trimestre> listarTrimestres();
}
