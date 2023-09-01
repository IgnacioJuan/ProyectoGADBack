package com.sistema.examenes.services;

import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.dto.PoaNoAprobadoDTO;
import com.sistema.examenes.dto.Poa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.projection.PoaNoAprobadoProjection;
import com.sistema.examenes.repository.PoaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
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
        return repository.listarPoadelProyectoconEstado(id_proyecto, estado);
    }

    @Override
    public List<Poa_DTO> listarPoasDeModelo() {
        List<Object[]> resultados = repository.listarPoasDeModelo();
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
            dto.setLinea_base((Double) result[7]);
            dto.setTipo_periodo((String) result[8]);
            poas.add(dto);
        }
        return poas;
    }

    @Override
    public List<PoaNoAprobadoDTO> listarPoaNoAprobados() {
        List<PoaNoAprobadoProjection> poaNoAprobados = repository.findNoAprobados();
        List<PoaNoAprobadoDTO> dtos = new ArrayList<>();

        for (PoaNoAprobadoProjection projection : poaNoAprobados) {
            PoaNoAprobadoDTO dto = new PoaNoAprobadoDTO();
            dto.setId_poa(projection.getId_poa());
            dto.setMeta_alcanzar(projection.getMeta_alcanzar());
            dto.setFecha_inicio(projection.getFecha_inicio());
            dto.setEstado(projection.getEstado());
            dto.setObservacion(projection.getObservacion());
            dtos.add(dto);
        }

        return dtos;
    }
}
