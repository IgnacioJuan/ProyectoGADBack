package com.sistema.examenes.services;

import com.sistema.examenes.dto.ObjetivoPnd_DTO;
import com.sistema.examenes.entity.ObjetivoPND;
import com.sistema.examenes.repository.ObjetivoPNDRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjetivoPND_ServiceImpl extends GenericServiceImpl<ObjetivoPND, Long> implements ObjetivoPND_Service {

    @Autowired
    private ObjetivoPNDRepository repository;

    @Override
    public CrudRepository<ObjetivoPND, Long> getDao() {
        return repository;
    }

    @Override
    public List<ObjetivoPND> listar() {
        return repository.listarObjetivosPNDS();
    }
    @Override
    public List<ObjetivoPnd_DTO> buscarObjetivosPNDSPorNombreDTO(String nombre) {
        List<Object[]> resultados = repository.buscarObjetivosPNDSPorNombre(nombre);
        List<ObjetivoPnd_DTO> objetivosEncontrados = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ObjetivoPnd_DTO objetivoDTO = new ObjetivoPnd_DTO(
                    (Long) resultado[0],
                    (String) resultado[1],
                    (String) resultado[2],
                    (Long) resultado[3],
                    (String) resultado[4]
            );
            objetivosEncontrados.add(objetivoDTO);
        }
        return objetivosEncontrados;
    }

}
