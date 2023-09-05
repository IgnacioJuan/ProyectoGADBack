package com.sistema.examenes.controller;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.dto.DetalleActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Componente;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.services.ActividadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/actividades")
public class ActividadesController {

    @Autowired
    private ActividadesService actividadesService;

    // post crear

    @PostMapping("/crear")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades a) {
        try {
            a.setVisible(true);
            a.setCodificado(0);
            return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get listar

    @GetMapping("/listar")
    public ResponseEntity<List<Actividades>> listar() {
        try {
            return new ResponseEntity<>(actividadesService.listarActividades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //listar actividades que tengan archivos rechazados
    @GetMapping("/listarActEviRechazados")
    public ResponseEntity<List<Actividades>> obtenerListarechazado() {
        try {
            return new ResponseEntity<>(actividadesService.listarActiEviRechazados(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Actividades actividades) {
        return ResponseEntity.status(HttpStatus.OK).body(actividadesService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        Actividades a = actividadesService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Actividades> actualizar(@PathVariable Long id, @RequestBody Actividades actividades) {

        try {
            Actividades a = actividadesService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setNombre(actividades.getNombre());
                a.setDescripcion(actividades.getDescripcion());
                a.setCodificado(actividades.getCodificado());
                a.setDevengado(actividades.getDevengado());
                a.setRecursos_propios(actividades.getRecursos_propios());
                a.setPresupuesto_referencial(actividades.getPresupuesto_referencial());
                a.setEstado(actividades.getEstado());
                a.setUsuario(actividades.getUsuario());
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarActividadesPoa/{poaId}")
    public List<ActividadDTO> listarActividadesPorIdPoa(@PathVariable Long poaId) {
        return actividadesService.listarActividadesPorIdPoa(poaId);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Actividades> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(actividadesService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // no borrar john es del gad
    @GetMapping("/actiresponsable/{id_resp}")
    public List<Actividades> listarActividadesPorResponsable(@PathVariable Long id_resp) {
        return actividadesService.listarActividadeSPORresponsable(id_resp);
    }

    //ni mio miriam
    @GetMapping("/listarUsuariosAsignadosAActividades")
    public List<UsuarioActividadesDTO> listarUsuariosAsignadosAActividades() {
        return actividadesService.listarUsuariosAsignadosAActividades();
    }

    // endpoints para ver el numero de actividades y las actividades del usuario
    @GetMapping("/usuactividades")
    public ResponseEntity<List<UsuarioActividadDTO>> obtenerUsuariosConActividades() {

        try {
            return new ResponseEntity<>(actividadesService.obtenerUsuariosConActividades(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detactividades/{idUsuario}")
    public ResponseEntity<List<DetalleActividadDTO>> obtenerDetalleActividades(@PathVariable Long idUsuario) {
        try {
            return new ResponseEntity<>(actividadesService.obtenerDetalleActividades(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/detactividadesaprobpoa/{id_poa}")
    public ResponseEntity<List<ActividadDTO>> obtenerDetalleActividadesAprob(@PathVariable Long id_poa) {
        try {
            return new ResponseEntity<>(actividadesService.obtenerDetalleActividadesAprob(id_poa), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/
    //mtodo post
    @PostMapping("/solicitud")
    public ResponseEntity<Actividades> crearActividad(@RequestParam String nombre, @RequestParam String descripcion,
                                                       @RequestParam Double recursos_propios, @RequestParam Double presupuesto_referencial) {
        Actividades a = new Actividades();
        a.setNombre(nombre);
        a.setDescripcion(descripcion);
        a.setRecursos_propios(recursos_propios);
        a.setPresupuesto_referencial(presupuesto_referencial);
        a.setEstado("PENDIENTE");
        a.setVisible(true);
        a.setCodificado(0);
        a.setDevengado(0);
        a.setUsuario(null);
        try {
            a.setVisible(true);
            return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
