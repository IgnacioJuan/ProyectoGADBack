package com.sistema.examenes.services;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;
import java.util.Optional;

public interface ActividadesService extends GenericService<Actividades, Long> {

    public List<Actividades> listarActividades();
    List<ActividadDTO> listarActividadesPorIdPoa(Long poaId);
    List<Actividades> listarActividadeSPORresponsable(Long id_resp);
    List<UsuarioActividadesDTO>listarUsuariosAsignadosAActividades();

    //void actualizarCodificado(Long idActividad, double valor);
    Optional<Actividades> findActividadById(Long id);

}
