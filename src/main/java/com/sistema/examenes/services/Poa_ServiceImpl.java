package com.sistema.examenes.services;

import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.dto.Poa_DTO;
import com.sistema.examenes.dto.ProyectoResumenDTO;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.repository.PoaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Poa_ServiceImpl extends GenericServiceImpl<Poa, Long> implements Poa_Service {

    @Autowired
    private PoaRepository repository;

    @Override
    public CrudRepository<Poa, Long> getDao() {
        return repository;
    }

    @Override
    public List<Poa> listar() {
        return repository.listarPoas();
    }

    @Override
    public Poa obtenerPoaId(Long id) {
        return repository.obtenerPoaId(id);
    }

    @Override
    public List<Poa> listarPoadelProyectoconEstado(Long id_proyecto, String estado) {
        return repository.listarPoadelProyectoconEstado(id_proyecto,estado);
    }
    public List<Poa_DTO> listarPoasAprobados() {
        List<Object[]> resultados = repository.listarPoasAprobados();
        List<Poa_DTO> poas = new ArrayList<>();

        for (Object[] result : resultados) {
            Poa_DTO dto = new Poa_DTO();
            dto.setId_poa(((BigInteger) result[0]).longValue());
            dto.setFecha_inicio((Date) result[1]);
            dto.setFecha_fin((Date) result[2]);
            dto.setLocalizacion((String) result[3]);
            dto.setCobertura((String) result[4]);
            dto.setBarrio((String) result[5]);
            dto.setComunidad((String) result[6]);
            dto.setNombre_funcionario((String) result[7]);
            dto.setCargo((String) result[8]);
            dto.setRecursos_propios((Double) result[9]);
            dto.setRecursos_externos((Double) result[10]);
            dto.setLinea_base((String) result[11]);
            poas.add(dto);
        }
        return poas;
    }
}

