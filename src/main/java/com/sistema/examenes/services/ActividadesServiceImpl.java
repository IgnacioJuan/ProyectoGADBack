package com.sistema.examenes.services;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.repository.ActividadesRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadesServiceImpl extends GenericServiceImpl<Actividades, Long> implements ActividadesService{

    @Autowired
    ActividadesRepository actividadesRepository;
    @Override
    public CrudRepository<Actividades, Long> getDao() {
        return actividadesRepository;
    }

    @Override
    public List<Actividades> listarActividades() {
        return actividadesRepository.listarActividades();
    }

    @Override
    public List<ActividadDTO> listarActividadesPorIdPoa(Long poaId) {
        List<Object[]> resultados = actividadesRepository.listarActividadesPorIdPoa(poaId);
        List<ActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ActividadDTO m = new ActividadDTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setNombre((String) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setPresupuesto_referencial((Double) resultado[3]);
            m.setRecursos_propios((Double) resultado[4]);
            m.setRecursos_externos((Double) resultado[5]);
            m.setCodificado((Double) resultado[6]);
            m.setEjecutado((Double) resultado[7]);
            m.setSaldo((Double) resultado[8]);
            acts.add(m);
        }
        return acts;
    }
}
