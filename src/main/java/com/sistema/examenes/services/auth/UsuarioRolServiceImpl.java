package com.sistema.examenes.services.auth;

import com.sistema.examenes.dto.UsuarioActividadDTO;
import com.sistema.examenes.dto.UsuarioResponsableDTO;
import com.sistema.examenes.entity.auth.UsuarioRol;
import com.sistema.examenes.repository.auth.UsuarioRolRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioRolServiceImpl extends GenericServiceImpl<UsuarioRol, Long> implements UsuarioRolService {
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

    @Override
    public List<UsuarioResponsableDTO> listarUResponsable() {

        List<Object[]> resultados = usuarioRolRepository.listarUResponsables();

        List<UsuarioResponsableDTO> usuarespon = new ArrayList<>();

        for (Object[] resultado : resultados) {

            UsuarioResponsableDTO usuarioResponsableDTO = new UsuarioResponsableDTO(
                    ((BigInteger) resultado[0]).longValue(),
                    (String) resultado[1],
                    (String) resultado[2],
                    (String) resultado[3],
                    (String) resultado[4],
                    (String) resultado[5],
                    (String) resultado[6],
                    (String) resultado[7]);

            usuarespon.add(usuarioResponsableDTO);
        }
        // Devolver la lista de DTOs.
        return usuarespon;
    }

}
