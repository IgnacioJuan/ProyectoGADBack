package com.sistema.examenes.services;

import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.dto.ProjectsActivesDto;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.repository.ProyectoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
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
    public List<Proyecto> listardelModelo(Long id_modelo_poa) {
        return repository.listarProyectosdelModelo(id_modelo_poa);
    }

    @Override
    public List<ProjectsActivesDto> listActiveProjects() {
        List<Object[]> results = repository.listActiveProjects();
        List<ProjectsActivesDto> projectsActive = new ArrayList<>();

        for(Object[] result : results){
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

    @Override
    public List<ProjectByIdDto> ProjectById(Long id_proyecto) {
        List<Object[]> results = repository.ProjectById(id_proyecto);
        List<ProjectByIdDto> projectById = new ArrayList<>();
        for(Object[] result:results){
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


}