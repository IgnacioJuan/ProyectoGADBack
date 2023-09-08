package com.sistema.examenes.services;

import com.sistema.examenes.dto.RTDecrementoActividadDTO;
import com.sistema.examenes.entity.ReformaTraspaso_D;
import com.sistema.examenes.repository.ReformaTraspaso_DRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReformaTraspaso_DServiceImpl extends GenericServiceImpl<ReformaTraspaso_D, Long> implements ReformaTraspaso_DService {

    @Autowired
    ReformaTraspaso_DRepository repository;
    @Override
    public CrudRepository<ReformaTraspaso_D, Long> getDao() {
        return repository;
    }

    @Override
    public List<ReformaTraspaso_D> listarReformasTraspaso_D() {
        return repository.listarReformasTraspaso_D();
    }

    public List<RTDecrementoActividadDTO> listarRTDActividades(Long actividadId) {
        List<Object[]> resultados = repository.listarRTDActividades(actividadId);
        List<RTDecrementoActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            RTDecrementoActividadDTO m = new RTDecrementoActividadDTO();
            m.setId_reftras_d(((BigInteger) resultado[0]).longValue());
            m.setValor((double) resultado[1]);
            m.setFecha((Date) resultado[2]);
            acts.add(m);
        }
        return acts;
    }
}
