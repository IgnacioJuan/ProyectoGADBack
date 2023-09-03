package com.sistema.examenes.services;

import com.sistema.examenes.dto.PresupuestoEActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.repository.PresupuestoExternoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PresupuestoExternoServiceImpl extends GenericServiceImpl<PresupuestoExterno, Long> implements PresupuestoExternoService {
    @Autowired
    PresupuestoExternoRepository repository;
    @Override
    public CrudRepository<PresupuestoExterno, Long> getDao() {
        return repository;
    }

    @Override
    public List<PresupuestoExterno> listarPresupuestoExterno() {
        return repository.listarPresupuestoExterno();
    }

    public List<PresupuestoEActividadDTO> listarPEActividades() {
        List<Object[]> resultados = repository.listarPEActividades();
        List<PresupuestoEActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            PresupuestoEActividadDTO m = new PresupuestoEActividadDTO();
            m.setId_presupuesto_externo(((BigInteger) resultado[0]).longValue());
            m.setNombre_institucion((String) resultado[1]);
            m.setValor((double) resultado[2]);
            m.setFecha((Date) resultado[3]);
            m.setObservacion((String) resultado[4]);
            m.setNombreActividad((String) resultado[5]);
            m.setNombreProyecto((String) resultado[6]);
            acts.add(m);
        }
        return acts;
    }
}
