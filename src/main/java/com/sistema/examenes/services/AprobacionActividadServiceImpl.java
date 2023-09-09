package com.sistema.examenes.services;

import com.sistema.examenes.entity.AprobacionActividad;
import com.sistema.examenes.projection.AprobacionporActividadProjection;
import com.sistema.examenes.repository.AprobacionActividadRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AprobacionActividadServiceImpl extends GenericServiceImpl<AprobacionActividad, Long> implements AprobacionActividadService{

    @Autowired
    AprobacionActividadRepository AprobacionActividadRepository;
    @Override
    public CrudRepository<AprobacionActividad, Long> getDao() {
        return AprobacionActividadRepository;
    }

    @Override
    public List<AprobacionActividad> listarAprobacionActividad() {
        return AprobacionActividadRepository.listarAprobacionActividad();
    }

    @Override
    public List<AprobacionporActividadProjection> listarAprobacionesporActividad(Long id_actividad) {
        return AprobacionActividadRepository.listarAprobacionesporActividad(id_actividad);
    }
}
