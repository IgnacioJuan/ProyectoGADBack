package com.sistema.examenes.repository.auth;

import com.sistema.examenes.entity.auth.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol,Long> {

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.usuario u WHERE u.visible = true")
    List<UsuarioRol> listarv();

    UsuarioRol findByUsuario_Id(Long usuarioId);

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.usuario u WHERE u.visible = true AND ur.rol.rolId = 3")
    List<UsuarioRol> listarUsuariosResponsables();


}
