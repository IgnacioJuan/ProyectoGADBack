package com.sistema.examenes.services;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.projection.PoasConActividadesPendientesProjection;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface Poa_Service extends GenericService<Poa, Long> {
    public List<Poa> listar();

    public Poa obtenerPoaId(Long id);

    public List<Poa> listarPoadelProyectoconEstado(Long id_proyecto, String estado);

    public List<Poa_DTO> listarPoasDeModelo();

    public List<PoaNoAprobadoDTO> listarPoaNoAprobados();
    
    public List<PoaporUsuarioDTO> listarPoaporUsuarios( Long id_proyecto );

    public List<AprobPoa_DTO> listarPoasparaAprobacion();
    
    public  List<Poa> findByIds(List<Long> ids);
    public List<PoasAdmin_DTO> listarPoasPorAdminEstado(Long idResponsable, String estado);
    public     List<PoasConActividadesPendientesProjection> PoasConActividadesPendientes();

}
