package com.sistema.examenes.services.auth;

import com.sistema.examenes.entity.auth.UsuarioRol;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface UsuarioRolService extends GenericService<UsuarioRol, Long> {

    public List<UsuarioRol> listarv();
    public UsuarioRol findByUsuario_UsuarioId(Long usuarioId);
}
