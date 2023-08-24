package com.sistema.examenes.services;

import com.sistema.examenes.entity.DetalleTrimestre;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface DetalleTrimestreService extends GenericService<DetalleTrimestre, Long> {

    List<DetalleTrimestre> listarDetalleTrimestres();
}
