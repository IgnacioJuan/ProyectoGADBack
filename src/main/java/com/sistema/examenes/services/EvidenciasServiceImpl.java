package com.sistema.examenes.services;

import com.sistema.examenes.entity.Evidencias;
import com.sistema.examenes.repository.EvidenciasRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenciasServiceImpl extends GenericServiceImpl<Evidencias, Long> implements EvidenciasService{

    @Autowired
    EvidenciasRepository evidenciasRepository;
    @Override
    public CrudRepository<Evidencias, Long> getDao() {
        return evidenciasRepository;
    }

    @Override
    public List<Evidencias> listarEvidencias() {
        return evidenciasRepository.listarEvidencias();
    }
}
