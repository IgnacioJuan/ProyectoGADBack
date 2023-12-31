package com.sistema.examenes.services;

import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.projection.ArchivoPoaProjection;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.repository.Archivo_repository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Archivo_ServiceImpl extends GenericServiceImpl<Archivo_s, Long> implements Archivo_Service {
    @Autowired
    private Archivo_repository repository;
    @Override
    public CrudRepository<Archivo_s, Long > getDao() {
        return repository;
    }

    @Override
    public List<Archivo_s> listar() {
        return repository.listararchivo();
    }

    //listar archivos rechazados
    @Override
    public List<Archivo_s> listararchirechazados() {return repository.listararchivorechazados();}
    public List<Archivo_s> listararchirechazados(Long idActividad) {return repository.listararchivorechazados();}

    //listar archivo enlace
    @Override
    public Archivo_s obtenerEnlacePorId(Long idArchivo) {
        return repository.obtenerEnlacePorId(idArchivo);
    }


    @Override
    public List<Archivo_s> listararchivouser(String username) {
        return repository.listararchivouser(username);
    }

    @Override
    public List<Archivo_s> listararchivoActividad(Long idActividad) {
        return repository.listararchivoActividad(idActividad);
    }

    @Override
    public List<ArchivoProjection> listararchi() {
        return null;
    }

    /*@Override
    public List<ArchivoProjection> listararchi() {
        return repository.listararchi();
    }*/
    @Override
    public List<Archivo_s> listarArchivosPorEstadoYUsuarioOrdenadoPorFechaDesc(String estado, String username) {
        return repository.listarArchivosPorEstadoYUsuarioOrdenadoPorFechaDesc(estado, username);
    }
    @Override
    public List<ArchivoPoaProjection> listarArchivosdelPoa(Long id_poa){
        return repository.listarArchivosdelPoa(id_poa);

    };

}
