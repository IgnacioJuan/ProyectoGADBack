package com.sistema.examenes.controller.auth;

import com.sistema.examenes.entity.auth.Rol;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.entity.auth.UsuarioRol;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.repository.auth.UsuarioRepository;
import com.sistema.examenes.services.auth.RolService;
import com.sistema.examenes.services.auth.UsuarioRolService;
import com.sistema.examenes.services.auth.UsuarioService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioRepository uR;
    @Autowired
    private UsuarioRolService userrol;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        Rol usuario1 = new Rol(1L, "ADMIN");
        Rol usuario2 = new Rol(2L, "SUPERADMIN");
        Rol usuario3 = new Rol(3L, "RESPONSABLE");
        Rol usuario4 = new Rol(4L, "AUTORIDAD");

        rolService.save(usuario1);
        rolService.save(usuario2);
        rolService.save(usuario3);
        rolService.save(usuario4);
    }
    // @PostMapping("/")
    // public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
    // Set<UsuarioRol> usuarioRoles = new HashSet<>();
    //
    // Rol rol = new Rol();
    // rol.setRolId(2L);
    // rol.setRolNombre("NORMAL");
    //
    // UsuarioRol usuarioRol = new UsuarioRol();
    // usuarioRol.setUsuario(usuario);
    // usuarioRol.setRol(rol);
    //
    // usuarioRoles.add(usuarioRol);
    // return usuarioService.guardarUsuario(usuario,usuarioRoles);

    // }

    @PostMapping("/crear/{rolId}")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario r, @PathVariable Long rolId) {
        try {
            if (usuarioService.obtenerUsuario(r.getUsername()) == null) {
                // Buscar el rol por ID
                Rol rol = rolService.findById(rolId);
                r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
                r.setVisible(true);
                // Crear un nuevo UsuarioRol y establecer las referencias correspondientes
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setUsuario(r);
                usuarioRol.setRol(rol);

                // Agregar el UsuarioRol a la lista de roles del usuario
                r.getUsuarioRoles().add(usuarioRol);

                // Guardar el usuario en la base de datos
                // Usuario nuevoUsuario = usuarioService.save(r);
                return new ResponseEntity<>(usuarioService.save(r), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> obtenerLista() {
        try {

            return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // listar solo los responsables
    @GetMapping("/listarResponsable")
    public ResponseEntity<List<Usuario>> obtenerListaResponsables() {
        try {
            // List<Usuario> responsables = uR.listaResponsables();
            return new ResponseEntity<>(uR.listaResponsables(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Usuario>> obtenerListav() {
        try {
            return new ResponseEntity<>(uR.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarResDatos")
    public ResponseEntity<List<Usuario>> obtenerListaRespoDatos() {
        try {

            return new ResponseEntity<>(uR.listaResponsablesDatos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarResponsableAdmin")
    public ResponseEntity<List<Usuario>> obtenerListaResponsableAdmin() {
        try {
            return new ResponseEntity<>(uR.listaResponsablesAdmin(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/responsables")
    public ResponseEntity<List<ResponsableProjection>> Responsables() {
        try {
            return new ResponseEntity<>(uR.responsables(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }

    @GetMapping("/buscaruser/{username}")
    public Usuario obtenerIdUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerId(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.delete(usuarioId);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarCliente(@PathVariable Long id, @RequestBody Usuario p) {
        Usuario usu = usuarioService.findById(id);
        UsuarioRol urol = userrol.findByUsuario_UsuarioId(id);
        if (usu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                usu.setPassword(this.bCryptPasswordEncoder.encode(p.getPassword()));
                return new ResponseEntity<>(usuarioService.save(usu), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Usuario a = usuarioService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(usuarioService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    // public List<Usuario> listaAdminDatos();
    @GetMapping("/listarAdminDatos")
    public ResponseEntity<List<Usuario>> obtenerListaAdminDatos() {
        try {

            return new ResponseEntity<>(uR.listaAdminDatos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Usamos el servicio para generar el reporte
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        // Configuración para permitir que el navegador visualice el PDF
        headers.add("Content-Disposition", "inline; filename=Users.pdf");

        return ResponseEntity.ok().headers(headers).body(usuarioService.exportPdf());
    }

    //

    // MODULO DE RESPONSABLE

    @PostMapping("/crearResponsable/{rolId}")
    public ResponseEntity<Usuario> crearResponsable(@RequestBody Usuario r, @PathVariable Long rolId) {
        try {
            if (usuarioService.obtenerUsuario(r.getUsername()) == null) {
                // Buscar el rol por ID
                Rol rol = rolService.findById(rolId);
                r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
                r.setVisible(true);
                // Crear un nuevo UsuarioRol y establecer las referencias correspondientes
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setUsuario(r);
                usuarioRol.setRol(rol);
                r.setPrograma(r.getPrograma());

                // Agregar el UsuarioRol a la lista de roles del usuario
                r.getUsuarioRoles().add(usuarioRol);

                // Guardar el usuario en la base de datos
                // Usuario nuevoUsuario = usuarioService.save(r);
                return new ResponseEntity<>(usuarioService.save(r), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarResponsable/{id}")
    public ResponseEntity<Usuario> actualizarResponsable(@RequestBody Usuario u, @PathVariable Long id) {

        try {
            Usuario usu = usuarioService.findById(id);
            if (usu != null) {
                 usu.setPassword(this.bCryptPasswordEncoder.encode(u.getPassword()));
                usu.setUsername(u.getUsername());
                usu.getPersona().setCedula(u.getPersona().getCedula());
                usu.getPersona().setPrimer_nombre(u.getPersona().getPrimer_nombre());
                usu.getPersona().setSegundo_nombre(u.getPersona().getSegundo_nombre());
                usu.getPersona().setPrimer_apellido(u.getPersona().getPrimer_apellido());
                usu.getPersona().setSegundo_apellido(u.getPersona().getSegundo_apellido());
                usu.getPersona().setDireccion(u.getPersona().getDireccion());
                usu.getPersona().setCorreo(u.getPersona().getCorreo());
                usu.getPersona().setCelular(u.getPersona().getCelular());
                usu.getPersona().setCargo(u.getPersona().getCargo());
            }
            return new ResponseEntity<>(usuarioService.save(usu), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/obtenerUResponsable/{id}")
    public ResponseEntity<Usuario> obtenerUResponsable(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
