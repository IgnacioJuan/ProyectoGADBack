package com.sistema.examenes.services;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.dto.PeriodoTotalPOA_DTO;
import com.sistema.examenes.dto.Periodo_DTO;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.repository.AprobacionPoaRepository;
import com.sistema.examenes.repository.EjeRepository;
import com.sistema.examenes.repository.PeriodoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Periodo_ServiceImpl extends GenericServiceImpl<Periodo, Long> implements Periodo_Service {

    @Autowired
    private PeriodoRepository repository;

    @Override
    public CrudRepository<Periodo, Long> getDao() {
        return repository;
    }

    @Override
    public List<Periodo> listar() {
        return repository.listarPeriodo();
    }

    @Override
    public List<Periodo_DTO> obtenerPorcentajeYReferenciaPorPoa(Long id_poa) {
        List<Object[]> resultados = repository.obtenerPorcentajeYReferenciaPorPoa(id_poa);
        List<Periodo_DTO> periodos = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Periodo_DTO periodoDTO = new Periodo_DTO(
                    (Double) resultado[0],
                    (Double) resultado[1]);
            periodos.add(periodoDTO);
        }
        return periodos;
    }

    @Override
    public PeriodoTotalPOA_DTO obtenerTotalesPorPoa(Long idPoa) {
        List<Object[]> resultados = repository.obtenerTotalesPorPoa(idPoa);
        PeriodoTotalPOA_DTO periodoTotalPOA_DTO = new PeriodoTotalPOA_DTO();

        for (Object[] resultado : resultados) {
            PeriodoTotalPOA_DTO periodosTotalPOA_DTO = new PeriodoTotalPOA_DTO(
                    (Double) resultado[0],
                    (Double) resultado[1],
                    (Double) resultado[2]);
            periodoTotalPOA_DTO = periodosTotalPOA_DTO;
        }
        return periodoTotalPOA_DTO;
    }

}
