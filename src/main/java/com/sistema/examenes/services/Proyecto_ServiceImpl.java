package com.sistema.examenes.services;

import com.sistema.examenes.dto.ProyectoResumenDTO;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.repository.ProyectoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Proyecto_ServiceImpl extends GenericServiceImpl<Proyecto, Long> implements Proyecto_Service {

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
    public List<ProyectoResumenDTO> listarProyectosConRelaciones() {
        List<Object[]> resultados = repository.listarProyectosConRelaciones();

        List<ProyectoResumenDTO> proyectos = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ProyectoResumenDTO proyectoDTO = new ProyectoResumenDTO();
            proyectoDTO.setId_proyecto((Long) resultado[0]);
            proyectoDTO.setCodigo((String) resultado[1]);
            proyectoDTO.setNombre((String) resultado[2]);
            proyectoDTO.setObjetivo((String) resultado[3]);
            proyectoDTO.setMeta((String) resultado[4]);
            proyectoDTO.setPorcentaje_alcance((Double) resultado[5]);
            proyectoDTO.setFecha_inicio((Date) resultado[6]);
            proyectoDTO.setId_objetivopnd((Long) resultado[7]);
            proyectoDTO.setNombre_objetivopnd((String) resultado[8]);
            proyectoDTO.setId_objetivoods((Long) resultado[9]);
            proyectoDTO.setNombre_objetivoods((String) resultado[10]);
            proyectoDTO.setId_modelopoa((Long) resultado[11]);
            proyectoDTO.setNombre_modelopoa((String) resultado[12]);
            proyectoDTO.setId_programa((Long) resultado[13]);
            proyectoDTO.setNombre_programa((String) resultado[14]);
            proyectoDTO.setId_indicador((Long) resultado[15]);
            proyectoDTO.setNombre_indicador((String) resultado[16]);
            proyectoDTO.setId_metapdot((Long) resultado[17]);
            proyectoDTO.setNombre_metapdot((String) resultado[18]);
            proyectoDTO.setId_objetivopdot((Long) resultado[19]);
            proyectoDTO.setNombre_objetivopdot((String) resultado[20]);
            proyectoDTO.setId_componente((Long) resultado[21]);
            proyectoDTO.setNombre_componente((String) resultado[22]);
            proyectoDTO.setId_competencia((Long) resultado[23]);
            proyectoDTO.setNombre_competencia((String) resultado[24]);

            proyectos.add(proyectoDTO);
        }
        return proyectos;
    }
}
