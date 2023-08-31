package com.sistema.examenes.services;
import com.sistema.examenes.repository.AprobacionEvidenciaRepository;
import com.sistema.examenes.entity.AprobacionEvidencia;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AprobacionEvidenciaServiceImpl  extends GenericServiceImpl<AprobacionEvidencia, Long> implements AprobacionEvidenciaService{
    @Autowired
    AprobacionEvidenciaRepository AprobacionEvidenciaRepository;
    @Override
    public List<AprobacionEvidencia> listarAprobacionEvidencia() {
        return AprobacionEvidenciaRepository.listarAprobacionEvidencia();
    }

    @Override
    public CrudRepository<AprobacionEvidencia, Long> getDao() {
        return AprobacionEvidenciaRepository;
    }
}
