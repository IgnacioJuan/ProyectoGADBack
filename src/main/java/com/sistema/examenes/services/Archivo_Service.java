package com.sistema.examenes.services;


import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Archivo_Service extends GenericService<Archivo_s, Long> {
    public List<Archivo_s> listar() ;

    public List<Archivo_s> listararchivouser(String username);
    public List<Archivo_s> listararchivoActividad(Long idActividad);

    public List<ArchivoProjection> listararchi() ;

    //listar archivos rechazados
    public List<Archivo_s> listararchirechazados() ;
    public List<Archivo_s> listararchirechazados(Long idActividad) ;

    //listar archivos enlace
    public Archivo_s obtenerEnlacePorId(Long idArchivo);

    public List<Archivo_s> listarArchivosPorEstadoYUsuarioOrdenadoPorFechaDesc(
            String estado, String username);
}
