package com.sistema.examenes.controller.auth;

import com.sistema.examenes.dto.UsuarioResponsableDTO;
import com.sistema.examenes.entity.auth.UsuarioRol;
import com.sistema.examenes.services.auth.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariorol")
@CrossOrigin("*")
public class Usuario_Rol_Controller {
    @Autowired
    private UsuarioRolService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/listarv")
    public ResponseEntity<List<UsuarioRol>> obtenerLista() {
        try {
            System.out.println(usuarioService.listarv());

            return new ResponseEntity<>(usuarioService.listarv(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarUsuariosResponsables/{poaId}")
    public ResponseEntity<List<UsuarioRol>> listarUsuariosResponsables2(@PathVariable Long poaId) {
        try {
            return new ResponseEntity<>(usuarioService.listarUsuariosResponsables2(poaId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarUsuarioSuperAdmin/{idPrograma}")
    public ResponseEntity<List<UsuarioRol>> listarUsuariosSuperAdmin(@PathVariable Long idPrograma) {
        try {
            return new ResponseEntity<>(usuarioService.listarUsuariosSuperAdmin(idPrograma), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarUResponsables/{programaUsuarioLogeado}")
    public ResponseEntity<List<UsuarioResponsableDTO>> listarUResponsables(@PathVariable Long programaUsuarioLogeado) {
        try {
            return new ResponseEntity<>(usuarioService.listarUResponsable(programaUsuarioLogeado), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{usuarioRolId}")
    public ResponseEntity<UsuarioRol> actualizarRol(@RequestBody UsuarioRol usuarioRol,
            @PathVariable Long usuarioRolId) {
        try {
            UsuarioRol usuarioRolExistente = usuarioService.findById(usuarioRolId);
            if (usuarioRolExistente != null) {
                String nuevaContraseña = usuarioRol.getUsuario().getPassword();
                // Actualizar la contraseña en el usuario existente
                if (!nuevaContraseña.equals(usuarioRolExistente.getUsuario().getPassword())) {
                    usuarioRolExistente.getUsuario().setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
                }
                usuarioRolExistente.setRol(usuarioRol.getRol());
                usuarioRolExistente.getUsuario().setPrograma(usuarioRol.getUsuario().getPrograma());
                usuarioRolExistente.getUsuario().setUsername(usuarioRol.getUsuario().getUsername());
                usuarioRolExistente.getUsuario().getPersona()
                        .setCedula(usuarioRol.getUsuario().getPersona().getCedula());
                usuarioRolExistente.getUsuario().getPersona()
                        .setPrimer_nombre(usuarioRol.getUsuario().getPersona().getPrimer_nombre());
                usuarioRolExistente.getUsuario().getPersona()
                        .setSegundo_nombre(usuarioRol.getUsuario().getPersona().getSegundo_nombre());
                usuarioRolExistente.getUsuario().getPersona()
                        .setPrimer_apellido(usuarioRol.getUsuario().getPersona().getPrimer_apellido());
                usuarioRolExistente.getUsuario().getPersona()
                        .setSegundo_apellido(usuarioRol.getUsuario().getPersona().getSegundo_apellido());
                usuarioRolExistente.getUsuario().getPersona()
                        .setDireccion(usuarioRol.getUsuario().getPersona().getDireccion());
                usuarioRolExistente.getUsuario().getPersona()
                        .setCorreo(usuarioRol.getUsuario().getPersona().getCorreo());
                usuarioRolExistente.getUsuario().getPersona()
                        .setCelular(usuarioRol.getUsuario().getPersona().getCelular());
                usuarioRolExistente.getUsuario().getPersona().setCargo(usuarioRol.getUsuario().getPersona().getCargo());
                UsuarioRol usuarioRolActualizado = usuarioService.save(usuarioRolExistente);
                return new ResponseEntity<>(usuarioRolActualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
