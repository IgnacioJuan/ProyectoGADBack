package com.sistema.examenes.services;


import com.sistema.examenes.entity.Aprobacion_SolicitudPresupuesto;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface AprobacionSolicitudPresupuesto_Service  extends GenericService<Aprobacion_SolicitudPresupuesto, Long> {
    public List<Aprobacion_SolicitudPresupuesto> listarAprobacionSolicitud();
}
