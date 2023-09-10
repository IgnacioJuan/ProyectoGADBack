package com.sistema.examenes.services;


import com.sistema.examenes.entity.Aprobacion_SolicitudPresupuesto;
import com.sistema.examenes.repository.AprobacionSolicitudPresupuesto_Repository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class AprobacionSolicitudPresupuesto_ServiceImpl extends GenericServiceImpl<Aprobacion_SolicitudPresupuesto, Long> implements AprobacionSolicitudPresupuesto_Service{
    @Autowired
    AprobacionSolicitudPresupuesto_Repository repository;
    @Override
    public List<Aprobacion_SolicitudPresupuesto> listarAprobacionSolicitud() {
        return repository.listarAprobacionSolicitud();
    }

    @Override
    public CrudRepository<Aprobacion_SolicitudPresupuesto, Long> getDao() {
        return repository;
    }

}
