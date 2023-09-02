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
    public List<Proyecto> listardelModelo(Long id_modelo_poa) {
        return repository.listarProyectosdelModelo(id_modelo_poa);
    }
    
     @Override
    public List<Proyecto> findByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}