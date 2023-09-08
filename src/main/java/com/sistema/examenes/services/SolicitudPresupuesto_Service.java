package com.sistema.examenes.services;

import com.sistema.examenes.dto.PoasAdmin_DTO;
import com.sistema.examenes.dto.SolicitudPresupuesto_DTO;
import com.sistema.examenes.entity.SolicitudPresupuesto;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface SolicitudPresupuesto_Service extends GenericService<SolicitudPresupuesto, Long> {

    public List<SolicitudPresupuesto> listarSolicitudPresupuesto();
    public List<SolicitudPresupuesto_DTO> listarPoasPorResponsableEstado(Long idResponsable, String estado);
}
