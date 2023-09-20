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


     /******* MODULO APROBACION POA ********/
  @Override
    public List<AprobPoa_DTO> obtenerPoasCompletos() {
        List<Object[]> resultados = AprobacionPoaRepository.obtenerPoasCompletos();
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
                    (Date) resultado[7],
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
                    (String) resultado[19],
                    (Double) resultado[20],
                    (Double) resultado[21],
                    (String) resultado[22],
                    (String) resultado[23],
                    (String) resultado[24],
                    (String) resultado[25],
                    (String) resultado[26],
                    (String) resultado[27]);
            aprobaciones.add(aprobPoaDTO);
        }
        return aprobaciones;
    }

    @Override
    public AprobPoa_DTO obtenerPoaCompletoPorId(Long idPoa) {
        List<Object[]> resultados = AprobacionPoaRepository.obtenerPoaCompletoPorId(idPoa);
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
                    (Date) resultado[7],
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
                    (String) resultado[19],
                    (Double) resultado[20],
                    (Double) resultado[21],
                    (String) resultado[22],
                    (String) resultado[23],
                    (String) resultado[24],
                    (String) resultado[25],
                    (String) resultado[26],
                    (String) resultado[27]);
            aprobaciones = aprobPoaDTO;
        }
        return aprobaciones;
    }
}
