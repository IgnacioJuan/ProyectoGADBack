package com.sistema.examenes.services;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.dto.PoaporUsuarioDTO;
import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.Poa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.projection.PoaNoAprobadoProjection;
import com.sistema.examenes.projection.PoaporFechaRepoProjection;
import com.sistema.examenes.projection.Poaactiprojection;
import com.sistema.examenes.projection.PoasConActividadesPendientesProjection;
import com.sistema.examenes.repository.AprobacionPoaRepository;
import com.sistema.examenes.repository.PoaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import com.sistema.examenes.util.ReporteMetas;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Poa_ServiceImpl extends GenericServiceImpl<Poa, Long> implements Poa_Service {

    @Autowired
    private PoaRepository repository;
    
    @Autowired
    private AprobacionPoaRepository repositoryAP;
    //Inyectamos el ReportGenerator
    @Autowired
    private ReporteMetas poaReporteMetas;

    public Poa_ServiceImpl(PoaRepository repository, AprobacionPoaRepository repositoryAP) {
        this.repository = repository;
        this.repositoryAP = repositoryAP;
    }

 

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
    public List<Poa> findByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<Poa_DTO> listarPoasProyectoDeModeloFiltroFechas() {
        List<Object[]> resultados = repository.listarPoasProyectoDeModeloFiltroFechas();
        List<Poa_DTO> poas = new ArrayList<>();

        for (Object[] result : resultados) {
            Poa_DTO dto = new Poa_DTO();
            dto.setId_poa(((BigInteger) result[0]).longValue());
            dto.setId_proyecto(((BigInteger) result[1]).longValue());
            dto.setNombreProyecto((String) result[2]);
            dto.setMeta_planificada((Double) result[3]);
            dto.setTipo_periodo((String) result[4]);
            dto.setFecha_inicio((Date) result[5]);
            dto.setFecha_fin((Date) result[6]);
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
            dto.setFecha_inicio(projection.getFecha_inicio());
            dto.setFecha_fin(projection.getFecha_fin());
            dto.setPrimer_nombre(projection.getPrimer_nombre());
            dto.setPrimer_apellido(projection.getPrimer_apellido());
            dto.setFecha_aprobacion(projection.getFecha_aprobacion());
            dto.setEstado(projection.getEstado());
            dto.setObservacion(projection.getObservacion());
            dto.setNombre(projection.getNombre());
            dtos.add(dto);
        }

        return dtos;
    }
  
     public List<PoaporUsuarioDTO> listarPoaporUsuarios(Long id_proyecto) {
        List<AprobacionPoa> poaporUsuario = repositoryAP.findPoaporUsuario(id_proyecto);
        List<PoaporUsuarioDTO> datos = new ArrayList<>();

        for (AprobacionPoa projection : poaporUsuario) {
            PoaporUsuarioDTO dato = new PoaporUsuarioDTO();
            dato.setPrimer_nombre(projection.getUsuario().getPersona().getPrimer_nombre());
            dato.setPrimer_apellido(projection.getUsuario().getPersona().getPrimer_apellido());
            dato.setCedula(projection.getUsuario().getPersona().getCedula());
            dato.setEstado(projection.getEstado());
            dato.setNombre(projection.getProyecto().getNombre());
            dato.setUsername(projection.getUsuario().getUsername());
            dato.setNombrepro(projection.getProyecto().getPrograma().getNombre());
            datos.add(dato); 

        }
         return datos;
    } 
     
     
     
     
    
    

    public List<AprobPoa_DTO> listarPoasparaAprobacion() {
        return null;
    }
    @Override
    public List<Poa> listarPoasjohn() {
        return repository.listarPoasjohn();
    }

    public List<PoasAdmin_DTO> listarPoasPorAdminEstado(Long idResponsable, String estado) {
        List<Object[]> resultados = repository.listarPoasPorAdminEstado(idResponsable, estado);
        List<PoasAdmin_DTO> poas = new ArrayList<>();

        for (Object[] result : resultados) {
            PoasAdmin_DTO dto = new PoasAdmin_DTO();
            dto.setNombre((String) result[0]);
            dto.setId_poa(((BigInteger) result[1]).longValue());
            dto.setBarrio((String) result[2]);
            dto.setCobertura((String) result[3]);
            dto.setComunidad((String) result[4]);
            dto.setFecha_inicio((Date) result[5]);
            dto.setFecha_fin((Date) result[6]);
            dto.setEstado((String) result[7]);
            dto.setLocalizacion((String) result[8]);

            poas.add(dto);
        }
        return poas;
    }

    @Override
    public List<PoasConActividadesPendientesProjection> PoasConActividadesPendientes() {
        return repository.PoasConActividadesPendientes();
    }

    @Override
    public List<Poaactiprojection> poaacjq(Long id) {
        return repository.poaacjq(id);
    }

    @Override
    public List<Poa> listarPoasPromedio() {
         return repository.listarPoasPromedio();
    }
    @Override
    public List<PoaporFechaRepoProjection> listarPoaApAdm(Long idResponsable){
        return repository.listarPoaApAdm(idResponsable);
    };

    public List<PoaSolicitudPresupuesto_DTO> listarPoasPorSolicitudPresupuesto(Long idAdmin) {
        List<Object[]> resultados = repository.listarPoasPorSolicitudPresupuesto(idAdmin);
        List<PoaSolicitudPresupuesto_DTO> poas = new ArrayList<>();

        for (Object[] result : resultados) {
            PoaSolicitudPresupuesto_DTO dto = new PoaSolicitudPresupuesto_DTO();
            dto.setNombre_proyecto((String) result[0]);
            dto.setId_poa(((BigInteger) result[1]).longValue());
            dto.setBarrio((String) result[2]);
            dto.setCobertura((String) result[3]);
            dto.setComunidad((String) result[4]);
            dto.setEstado_poa((String) result[5]);
            dto.setMeta_alcanzar((Double) result[6]);
            dto.setMeta_planificada((Double) result[7]);
            poas.add(dto);
        }
        return poas;
    }

    public List<Poas_Indicadores_DTO> listarPoasMetasIndicadores() {
        List<Object[]> resultados = repository.listarPoasMetasIndicadores();
        List<Poas_Indicadores_DTO> poas = new ArrayList<>();

        for (Object[] result : resultados) {
            Poas_Indicadores_DTO dto = new Poas_Indicadores_DTO();
            dto.setNombre_proyecto((String) result[0]);
            dto.setId_poa(((BigInteger) result[1]).longValue());
            dto.setLocalizacion((String) result[2]);
            dto.setTipo_periodo((String) result[3]);
            dto.setLinea_base((Double) result[4]);
            dto.setMeta_alcanzar((Double) result[5]);
            dto.setMeta_planificada((Double) result[6]);
            dto.setTipo_evaluacion((String) result[7]);
            dto.setNombre_metapdot((String) result[8]);
            dto.setPorcentaje_cumplimiento((BigDecimal) result[9]);

            poas.add(dto);
        }
        return poas;
    }

    //Llamamos al ReportGenerator
    @Override
    public byte[] exportPdfMETAS() throws JRException, FileNotFoundException {


        List<Object[]> resultados = repository.listarPoasMetasIndicadores();
        List<Poas_Indicadores_DTO> poas = new ArrayList<>();

        for (Object[] result : resultados) {
            Poas_Indicadores_DTO dto = new Poas_Indicadores_DTO();
            dto.setNombre_proyecto((String) result[0]);
            dto.setId_poa(((BigInteger) result[1]).longValue());
            dto.setLocalizacion((String) result[2]);
            dto.setTipo_periodo((String) result[3]);
            dto.setLinea_base((Double) result[4]);
            dto.setMeta_alcanzar((Double) result[5]);
            dto.setMeta_planificada((Double) result[6]);
            dto.setTipo_evaluacion((String) result[7]);
            dto.setNombre_metapdot((String) result[8]);
            dto.setPorcentaje_cumplimiento((BigDecimal) result[9]);
            poas.add(dto);
        }
        return poaReporteMetas.exportToPdfMetas(poas);
    }

}
