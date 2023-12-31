package com.sistema.examenes.services;

import com.sistema.examenes.dto.ObjetivoPdot_DTO;
import com.sistema.examenes.entity.Objetivo_pdot;
import com.sistema.examenes.repository.ObjetivoPdotRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObjetivoPdot_ServiceImpl extends GenericServiceImpl<Objetivo_pdot, Long> implements ObjetivoPdot_Service {

    @Autowired
    private ObjetivoPdotRepository repository;

    @Override
    public CrudRepository<Objetivo_pdot, Long> getDao() {
        return repository;
    }

    @Override
    public List<Objetivo_pdot> listarObjetivosPdots() {
        return repository.listarObjetivosPdots();
    }

    @Override
    public Objetivo_pdot obtenerObjetivoPdotId(Long id) {
        return repository.obtenerObjetivoPdotId(id);
    }

    @Override
    public List<ObjetivoPdot_DTO> buscarObjetivosPdotsPorNombre(String nombre) {
        List<Object[]> resultados = repository.buscarObjetivosPdotsPorNombre(nombre);
        List<ObjetivoPdot_DTO> objetivosEncontrados = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ObjetivoPdot_DTO objetivoDTO = new ObjetivoPdot_DTO(
                    (Long) resultado[0],
                    (String) resultado[1],
                    (String) resultado[2]
            );
            objetivosEncontrados.add(objetivoDTO);
        }
        return objetivosEncontrados;
    }

    @Override
    public List<ObjetivoPdot_DTO> listarObjetivosPdotsDTOPorIdComponente(Long idComponente) {
        List<Object[]> resultados = repository.listarObjetivosPdotsDTOPorIdComponente(idComponente);
        List<ObjetivoPdot_DTO> objs = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ObjetivoPdot_DTO obj = new ObjetivoPdot_DTO();
            obj.setId_objetivo_pdot(((BigInteger) resultado[0]).longValue());
            obj.setNombre((String) resultado[1]);
            obj.setDescripcion((String) resultado[2]);
            objs.add(obj);
        }
        return objs;
    }
}
