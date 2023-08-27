package com.sistema.examenes.services;


import com.sistema.examenes.dto.Programa_DTO;
import com.sistema.examenes.entity.Programa;
import com.sistema.examenes.repository.ProgramaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class Programa_ServiceImpl extends GenericServiceImpl<Programa, Long> implements Programa_Service {

    @Autowired
    private ProgramaRepository repository;

    @Override
    public CrudRepository<Programa, Long> getDao() {
        return repository;
    }

    @Override
    public List<Programa> listar() {
        return repository.listarProgramas();
    }
    @Override
    public List<Programa_DTO> buscarProgramasPorNombreDTO(String nombre) {
        List<Object[]> resultados = repository.buscarProgramasPorNombre(nombre);
        List<Programa_DTO> programasEncontrados = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Programa_DTO programaDTO = new Programa_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2]
            );
            programasEncontrados.add(programaDTO);
        }
        return programasEncontrados;
    }

}
