package com.sistema.examenes.services;

import com.sistema.examenes.dto.PoasAdmin_DTO;
import com.sistema.examenes.dto.SolicitudPresupuesto_DTO;
import com.sistema.examenes.entity.SolicitudPresupuesto;
import com.sistema.examenes.repository.SolicitudPresupuestoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SolicitudPresupuesto_ServiceImpl extends GenericServiceImpl<SolicitudPresupuesto, Long> implements SolicitudPresupuesto_Service {

    @Autowired
    private SolicitudPresupuestoRepository repository;

    @Override
    public CrudRepository<SolicitudPresupuesto, Long> getDao() {
        return repository;
    }

    @Override
    public List<SolicitudPresupuesto> listarSolicitudPresupuesto() {
        return repository.listarSolicitudPresupuesto();
    }


    public List<SolicitudPresupuesto_DTO> listarPoasPorResponsableEstado(Long idResponsable, String estado) {
        List<Object[]> resultados = repository.listarPoasPorResponsableEstado(idResponsable, estado);
        List<SolicitudPresupuesto_DTO> solicitud = new ArrayList<>();

        for (Object[] result : resultados) {
            SolicitudPresupuesto_DTO dto = new SolicitudPresupuesto_DTO();
            dto.setId_solicitud_presupuesto(((BigInteger) result[0]).longValue());
            dto.setEstado((String) result[1]);
            dto.setFecha_solicitud((Date) result[2]);
            dto.setMonto_actual((double) result[3]);
            dto.setMonto_total((double) result[4]);
            dto.setMotivo((String) result[5]);
            dto.setReforma((double) result[6]);
            dto.setResponsable_username((String) result[7]);
            dto.setSuperadmin_username((String) result[8]);
            dto.setActividad((String) result[9]);
            dto.setCargo_responsable((String) result[10]);
            dto.setPrimer_nombre_responsable((String) result[11]);
            dto.setPrimer_apellido_responsable((String) result[12]);
            dto.setCargo_superadmin((String) result[13]);
            dto.setPrimer_nombre_superadmin((String) result[14]);
            dto.setPrimer_apellido_superadmin((String) result[15]);
            solicitud.add(dto);
        }
        return solicitud;
    }

}
