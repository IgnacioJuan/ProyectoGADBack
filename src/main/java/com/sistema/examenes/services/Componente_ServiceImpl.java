package com.sistema.examenes.services;

import com.sistema.examenes.dto.Componente_DTO;
import com.sistema.examenes.entity.Componente;
import com.sistema.examenes.repository.ComponenteRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class Componente_ServiceImpl extends GenericServiceImpl<Componente, Long> implements Componente_Service {

    @Autowired
    private ComponenteRepository repository;

    @Override
    public CrudRepository<Componente, Long> getDao() {
        return repository;
    }

    @Override
    public List<Componente> listar() {
        return repository.listarComponentes();
    }

    @Override
    public List<Componente_DTO> listarc() {
        List<Object[]> resultados = repository.listarc();
        List<Componente_DTO> componentesEncontrados = new ArrayList<>();

        for (Object[] result : resultados) {
            Componente_DTO dto = new Componente_DTO();
            dto.setId_componente(((BigInteger) result[0]).longValue());
            dto.setNombre((String) result[1]);
            dto.setDescripcion((String) result[2]);
            componentesEncontrados.add(dto);
        }

        return componentesEncontrados;
    }

    @Override
    public Componente obtenerComponenteId(Long id) {
        return repository.obtenerComponenteId(id);
    }

    @Override
    public List<Componente_DTO> buscarComponentesPorNombre(String nombre) {
        List<Object[]> resultados = repository.buscarComponentesPorNombre(nombre);
        List<Componente_DTO> componentesEncontrados = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Componente_DTO componenteDTO = new Componente_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2]
            );
            componentesEncontrados.add(componenteDTO);
        }
        return componentesEncontrados;
    }
}
