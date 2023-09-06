package com.sistema.examenes.services;

import com.sistema.examenes.dto.RTIncrementoActividadDTO;
import com.sistema.examenes.dto.ReformaSActividadDTO;
import com.sistema.examenes.entity.ReformaTraspaso_I;
import com.sistema.examenes.repository.ReformaTraspaso_IRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReformaTraspaso_IServiceImpl extends GenericServiceImpl<ReformaTraspaso_I, Long> implements ReformaTraspaso_IService {

    @Autowired
    ReformaTraspaso_IRepository repository;
    @Override
    public CrudRepository<ReformaTraspaso_I, Long> getDao() {
        return repository;
    }

    @Override
    public List<ReformaTraspaso_I> listarReformasTraspaso_I() {
        return repository.listarReformasTraspaso_I();
    }
    public List<RTIncrementoActividadDTO> listarRTIActividades() {
        List<Object[]> resultados = repository.listarRTIActividades();
        List<RTIncrementoActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            RTIncrementoActividadDTO m = new RTIncrementoActividadDTO();
            m.setId_reftras_i(((BigInteger) resultado[0]).longValue());
            m.setValor((double) resultado[1]);
            m.setFecha((Date) resultado[2]);
            m.setNombreActividad((String) resultado[3]);
            m.setNombreProyecto((String) resultado[4]);
            acts.add(m);
        }
        return acts;
    }
}
