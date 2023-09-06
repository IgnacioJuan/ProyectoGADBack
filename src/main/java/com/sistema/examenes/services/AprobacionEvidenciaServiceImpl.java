package com.sistema.examenes.services;
import com.sistema.examenes.dto.AprobacionEvidenciaDTO;
import com.sistema.examenes.dto.ObjetivoPdot_DTO;
import com.sistema.examenes.repository.AprobacionEvidenciaRepository;
import com.sistema.examenes.entity.AprobacionEvidencia;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
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
    @Override
    public List<AprobacionEvidenciaDTO> listarAprobacionEvidenciaPorIdArchivo(Long archivoId) {
        List<Object[]> resultados = AprobacionEvidenciaRepository.listarAprobacionEvidenciaPorIdArchivo(archivoId);
        List<AprobacionEvidenciaDTO> aprobEvid = new ArrayList<>();
        for (Object[] resultado : resultados) {
            AprobacionEvidenciaDTO obj = new AprobacionEvidenciaDTO();
            obj.setObservacion((String) resultado[0]);
            obj.setEstado((String) resultado[1]);
            obj.setId_aprobacionevid(((BigInteger) resultado[2]).longValue());
            obj.setPrimer_nombre((String) resultado[3]);
            obj.setPrimer_apellido((String) resultado[4]);

            aprobEvid .add(obj);
        }
        return aprobEvid ;
    }
}
