package com.sistema.examenes.services;

import com.sistema.examenes.dto.PresupuestoEActividadDTO;
import com.sistema.examenes.dto.ReformaSActividadDTO;
import com.sistema.examenes.entity.ReformaSuplemento;
import com.sistema.examenes.repository.ReformaSuplementoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReformaSuplementoServiceImpl extends GenericServiceImpl<ReformaSuplemento, Long> implements ReformaSuplementoService {
    @Autowired
    ReformaSuplementoRepository repository;

    @Override
    public CrudRepository<ReformaSuplemento, Long> getDao() {
        return repository;
    }

    @Override
    public List<ReformaSuplemento> listarReformaSuplemento() {
        return repository.listarReformaSuplemento();
    }

    public List<ReformaSActividadDTO> listarRSActividades() {
        List<Object[]> resultados = repository.listarRSActividades();
        List<ReformaSActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ReformaSActividadDTO m = new ReformaSActividadDTO();
            m.setId_ref_suplemento(((BigInteger) resultado[0]).longValue());
            m.setValor((double) resultado[1]);
            m.setFecha((Date) resultado[2]);
            m.setNombreActividad((String) resultado[3]);
            m.setNombreProyecto((String) resultado[4]);
            acts.add(m);
        }
        return acts;
    }
}
