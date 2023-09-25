package com.sistema.examenes.services;

import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.dto.ProjectsActivesDto;
import com.sistema.examenes.dto.ProyectoExportarexcelDTO;
import com.sistema.examenes.dto.reportePresupuestoDTO;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.repository.ProyectoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import com.sistema.examenes.util.PresupuestoReport;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;

@Service
public class Proyecto_ServiceImpl extends GenericServiceImpl<Proyecto, Long> implements Proyecto_Service {

    @Autowired
    private PresupuestoReport reportepresupuesto;
    
    
    @Autowired
    private ProyectoRepository repository;

    @Override
    public CrudRepository<Proyecto, Long> getDao() {
        return repository;
    }

    @Override
    public List<Proyecto> listar() {
        return repository.listarProyectos();
    }

    @Override
    public List<Proyecto> listardelModelo(Long id_modelo_poa) {
        return repository.listarProyectosdelModelo(id_modelo_poa);
    }

    @Override
    public List<ProyectoExportarexcelDTO> exportarexcel(Long id_modelo_poa) {
        List<Proyecto> exportar = repository.exportarexcel(id_modelo_poa);
        List<ProyectoExportarexcelDTO> datos = new ArrayList<>();

        for (Proyecto projection : exportar) {
            ProyectoExportarexcelDTO dato = new ProyectoExportarexcelDTO();
            dato.setId_proyecto(projection.getId_proyecto());
            dato.setNombre(projection.getNombre());
            dato.setCodigo(projection.getCodigo());
            dato.setNombre_objetivoods(projection.getOds().getNombre());
            dato.setNombre_objetivopnd(projection.getPnd().getNombre());
            dato.setNombre_objetivopdot(projection.getIndicador().getMetapdot().getObjetivopdot().getNombre());
            dato.setNombre_metapdot(projection.getIndicador().getMetapdot().getNombre());
            dato.setNombre_indicador(projection.getIndicador().getNombre());
            dato.setNombre_programa(projection.getPrograma().getNombre());
            dato.setObjetivo(projection.getObjetivo());
            dato.setMeta(projection.getMeta());
            dato.setNombre_competencia(projection.getCompetencia().getNombre());
            datos.add(dato);
        }
        return datos;
    }

    @Override
    public List<Proyecto> findByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<ProjectsActivesDto> listActiveProjects(Long id_usuario) {
        List<Object[]> results = repository.listActiveProjects(id_usuario);
        List<ProjectsActivesDto> projectsActive = new ArrayList<>();

        for (Object[] result : results) {
            ProjectsActivesDto project = new ProjectsActivesDto(
                    ((BigInteger) result[0]).longValue(),
                    (String) result[1],
                    (String) result[2],
                    (String) result[3]
            );
            projectsActive.add(project);
        }

        return projectsActive;
    }

//    @Override
//    public List<ProjectsActivesDto> listActiveProjects(Long id_usuario
//        List<Object[]> results = repository
//        List<ProjectsActivesDto> projectsActive = new ArrayList<>();
//
//        for(Object[] result : results){
//            ProjectsActivesDto project = new ProjectsActivesDto(
//                    ((BigInteger) result[0]).longValue(),
//                    (String) result[1],
//                    (String) result[2],
//                    (String) result[3]
//            );
//            projectsActive.add(project);
//        }
//
//        return projectsActive;
//    }
    @Override
    public List<ProjectByIdDto> ProjectById(Long id_proyecto) {
        List<Object[]> results = repository.ProjectById(id_proyecto);
        List<ProjectByIdDto> projectById = new ArrayList<>();
        for (Object[] result : results) {
            ProjectByIdDto project = new ProjectByIdDto(
                    ((BigInteger) result[0]).longValue(),
                    ((BigInteger) result[1]).longValue(),
                    (String) result[2],
                    (String) result[3],
                    (String) result[4],
                    (String) result[5],
                    (String) result[6],
                    (String) result[7],
                    (String) result[8],
                    (String) result[9],
                    (String) result[10],
                    (String) result[11],
                    (String) result[12],
                    (String) result[13],
                    (String) result[14],
                    (String) result[15]
            );
            projectById.add(project);
        }
        return projectById;
    }

    @Override
    public Long secuenciaproyecto(String codigo) {
        return repository.SecuenciadelCodigo(codigo);
    }

    @Override
    public List<reportePresupuestoDTO> obtenerReportePresupuesto() {
        List<Object[]> results = repository.listarProyectosReporte();
        List<reportePresupuestoDTO> reportepresupuesto = new ArrayList<>();
        
        for(Object[] result:results){
        reportePresupuestoDTO presupuesto= new reportePresupuestoDTO((String) result[0],(Double) result[1] , (Double) result[2],(BigDecimal) result[3]);
        
        reportepresupuesto.add(presupuesto);
        }
        return reportepresupuesto;
    }

    @Override
    public byte[] exportPdfMETAS() throws JRException, FileNotFoundException {
List<Object[]> resultados = repository.listarProyectosReporte();
        List<reportePresupuestoDTO> listareporte = new ArrayList<>();
        for (Object[] result : resultados) {
            reportePresupuestoDTO dto = new reportePresupuestoDTO();
            dto.setNombreProyecto((String) result[0]);
            dto.setMontoCodificado((Double) result[1]);
            dto.setMontoDevengado((Double) result[2]);
            dto.setPejecucion((BigDecimal) result[3]);
            listareporte.add(dto);
        }
        return reportepresupuesto.exportToPdfMetas(listareporte);    }

}
