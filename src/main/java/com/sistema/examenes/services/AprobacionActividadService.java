package com.sistema.examenes.services;

import com.sistema.examenes.entity.AprobacionActividad;
import com.sistema.examenes.projection.AprobacionporActividadProjection;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface AprobacionActividadService extends GenericService<AprobacionActividad, Long> {

    public List<AprobacionActividad> listarAprobacionActividad();

    public List<AprobacionporActividadProjection> listarAprobacionesporActividad(Long id_actividad);

}
