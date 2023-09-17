package com.sistema.examenes.repository.auth;

import com.sistema.examenes.dto.UsuarioResponsableDTO;
import com.sistema.examenes.entity.auth.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.usuario u WHERE u.visible = true")
    List<UsuarioRol> listarv();

    UsuarioRol findByUsuario_Id(Long usuarioId);

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.usuario u WHERE u.visible = true AND ur.rol.rolId = 3")
    List<UsuarioRol> listarUsuariosResponsables();

    @Query(value = "SELECT " +
            "    u.id AS id_usuario_responsable, " +
            "    p.primer_nombre, " +
            "    p.segundo_nombre, " +
            "    p.primer_apellido, " +
            "    p.segundo_apellido, " +
            "    u.username AS usuario, " +
            "    prog.nombre AS programa, " +
            "    p.cargo " +
            "FROM public.usuariorol ur " +
            "JOIN public.usuarios u ON ur.usuario_id = u.id " +
            "JOIN public.persona p ON u.persona_id_persona = p.id_persona " +
            "JOIN public.programa prog ON u.id_programa = prog.id_programa " +
            "WHERE ur.rol_rolid = 3 AND u.visible = true", nativeQuery = true)
    List<Object[]> listarUResponsables();

}
