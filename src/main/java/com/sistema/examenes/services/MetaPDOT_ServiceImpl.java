package com.sistema.examenes.services;


import com.sistema.examenes.dto.MetaPdot_DTO;
import com.sistema.examenes.entity.MetaPDOT;
import com.sistema.examenes.repository.MetaPDOTRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetaPDOT_ServiceImpl extends GenericServiceImpl<MetaPDOT, Long> implements MetaPDOT_Service {

    @Autowired
    private MetaPDOTRepository repository;

    @Override
    public CrudRepository<MetaPDOT, Long> getDao() {
        return repository;
    }

    @Override
    public List<MetaPDOT> listar() {
        return repository.listarMetaPDOTs();
    }

    @Override
    public MetaPDOT obtenerMetaPdotId(Long id) {
        return repository.obtenerMetaPdotId(id);
    }

    @Override
    public List<MetaPdot_DTO> buscarMetasPdotsPorNombre(String nombre) {
        List<Object[]> resultados = repository.buscarMetasPdotsPorNombre(nombre);
        List<MetaPdot_DTO> metasEncontradas = new ArrayList<>();

        for (Object[] resultado : resultados) {
            MetaPdot_DTO metaDTO = new MetaPdot_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2],
                    (double) resultado[3]
            );
            metasEncontradas.add(metaDTO);
        }
        return metasEncontradas;
    }
}
