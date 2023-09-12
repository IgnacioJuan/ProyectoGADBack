package com.sistema.examenes.services.auth;

import com.sistema.examenes.entity.auth.UsuarioRol;
import com.sistema.examenes.repository.auth.UsuarioRolRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolServiceImpl extends GenericServiceImpl<UsuarioRol, Long> implements UsuarioRolService{
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;
    @Override
    public CrudRepository<UsuarioRol, Long> getDao() {
        return usuarioRolRepository;
    }

    @Override
    public List<UsuarioRol> listarv() {
        return usuarioRolRepository.listarv();
    }

    @Override
    public List<UsuarioRol> listarUsuariosResponsables() {
        return usuarioRolRepository.listarUsuariosResponsables();
    }
    public List<UsuarioRol> listarUsuariosSuperAdmin() {
        return usuarioRolRepository.listarUsuariosSuperAdmin();
    }


    @Override
    public UsuarioRol findByUsuario_UsuarioId(Long usuarioId) {
        return usuarioRolRepository.findByUsuario_Id(usuarioId);
    }


}
