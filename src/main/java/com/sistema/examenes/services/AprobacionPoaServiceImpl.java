package com.sistema.examenes.services;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.AprobacionPoa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.repository.AprobacionPoaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AprobacionPoaServiceImpl extends GenericServiceImpl<AprobacionPoa, Long> implements AprobacionPoaService {

    @Autowired
    AprobacionPoaRepository AprobacionPoaRepository;

    @Override
    public CrudRepository<AprobacionPoa, Long> getDao() {
        return AprobacionPoaRepository;
    }

    @Override
    public List<AprobacionPoa> listarAprobacionPoa() {
        return AprobacionPoaRepository.listarAprobacionPoa();
    }

    @Override
    public List<AprobPoa_DTO> obtenerAprobacionesPoa() {
        List<Object[]> resultados = AprobacionPoaRepository.obtenerAprobacionesPoa();
        List<AprobPoa_DTO> aprobaciones = new ArrayList<>();

        for (Object[] resultado : resultados) {
            AprobPoa_DTO aprobPoaDTO = new AprobPoa_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2],
                    (String) resultado[3],
                    (String) resultado[4],
                    (Date) resultado[5],
                    (Date) resultado[6],
                    (String) resultado[7],
                    (String) resultado[8],
                    (String) resultado[9],
                    (String) resultado[10],
                    (String) resultado[11],
                    (String) resultado[12],
                    (String) resultado[13],
                    (String) resultado[14],
                    (String) resultado[15],
                    (String) resultado[16],
                    (String) resultado[17],
                    (String) resultado[18],
                    (Double) resultado[19],
                    (String) resultado[20],
                    (String) resultado[21],
                    (String) resultado[22],
                    (String) resultado[22],
                    (String) resultado[23],
                    (String) resultado[25]);
            aprobaciones.add(aprobPoaDTO);
        }
        return aprobaciones;
    }

    @Override
    public AprobPoa_DTO obtenerAprobacionPoaPorId(Long idPoa) {
        List<Object[]> resultados = AprobacionPoaRepository.obtenerAprobacionPoaPorId(idPoa);
        AprobPoa_DTO aprobaciones = new AprobPoa_DTO();

        for (Object[] resultado : resultados) {
            AprobPoa_DTO aprobPoaDTO = new AprobPoa_DTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2],
                    (String) resultado[3],
                    (String) resultado[4],
                    (Date) resultado[5],
                    (Date) resultado[6],
                    (String) resultado[7],
                    (String) resultado[8],
                    (String) resultado[9],
                    (String) resultado[10],
                    (String) resultado[11],
                    (String) resultado[12],
                    (String) resultado[13],
                    (String) resultado[14],
                    (String) resultado[15],
                    (String) resultado[16],
                    (String) resultado[17],
                    (String) resultado[18],
                    (Double) resultado[19],
                    (String) resultado[20],
                    (String) resultado[21],
                    (String) resultado[22],
                    (String) resultado[22],
                    (String) resultado[23],
                    (String) resultado[25]);
            aprobaciones = aprobPoaDTO;
        }
        return aprobaciones;
    }

    @Override
    public AprobacionPoa obtenerAprobacionPorIdPoa(Long id_poa) {
        return AprobacionPoaRepository.findByPoaId(id_poa);
    }

    public List<AprobacionPoa_DTO> listarAprobacionPoaPorIdPoa(Long idPoa) {
        List<Object[]> resultados = AprobacionPoaRepository.listarAprobacionPoaPorIdPoa(idPoa);
        List<AprobacionPoa_DTO> aprobPoa = new ArrayList<>();
        for (Object[] resultado : resultados) {
            AprobacionPoa_DTO obj = new AprobacionPoa_DTO();
            obj.setObservacion((String) resultado[0]);
            obj.setEstado((String) resultado[1]);
            obj.setId_aprobacionpoa(((BigInteger) resultado[2]).longValue());
            obj.setPrimer_nombre((String) resultado[3]);
            obj.setPrimer_apellido((String) resultado[4]);
            obj.setFecha_aprobacion((Date) resultado[5]);
            aprobPoa .add(obj);
        }
        return aprobPoa ;
    }

}
