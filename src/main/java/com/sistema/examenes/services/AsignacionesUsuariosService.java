package com.sistema.examenes.services;



import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.AsignacionesUsuarios;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface AsignacionesUsuariosService extends GenericService<AsignacionesUsuarios, Long> {
    public List<AsignacionesUsuarios> listar();

    public List<UsuarioActividadesDTO>listarAsignacionesUsuarios(Long actividadId);

}
