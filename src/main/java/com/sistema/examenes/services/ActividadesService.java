package com.sistema.examenes.services;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ActividadesService extends GenericService<Actividades, Long> {

    public List<Actividades> listarActividades();
    List<ActividadDTO> listarActividadesPorIdPoa(Long poaId);

    List<UsuarioActividadesDTO>listarUsuariosAsignadosAActividades();
}
