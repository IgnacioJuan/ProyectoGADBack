package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.AprobacionActividad;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.projection.AprobacionporActividadProjection;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.AprobacionActividadService;
import com.sistema.examenes.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/aprobacionactividad")
public class AprobacionActividadController {

    @Autowired
    private AprobacionActividadService AprobacionActividadService;
    @Autowired
    private ActividadesService actividadesService;
    //post crear
    @Autowired
    private IEmailService emailService;
    @PostMapping("/crear")
    public ResponseEntity<AprobacionActividad> crear(@RequestBody AprobacionActividad a){
        try {
            a.setVisible(true);
            AprobacionActividad ap = AprobacionActividadService.save(a);
            actividadesService.actualizarEstadoPorAprobacion(a.getActividad().getId_actividad(), a.getEstado());

            // Obtener el usuario responsable a través de la actividad -> poa -> usuarios
            Usuario usuario = actividadesService.findById(a.getActividad().getId_actividad()).getPoa().getUsuario();

            // Verificar si el usuario y su información de persona no son null antes de enviar el correo electrónico
            if (usuario != null && usuario.getPersona() != null && usuario.getPersona().getCorreo() != null) {
                emailService.sendEmail(new String[]{usuario.getPersona().getCorreo()}, ap.getEstado(), ap.getObservacion());
            }
            return new ResponseEntity<>(ap, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/crearAprobacion")
    public ResponseEntity<AprobacionActividad> crearActividad(@RequestBody AprobacionActividad r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(AprobacionActividadService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<AprobacionActividad>> listar(){
        try {
            return new ResponseEntity<>(AprobacionActividadService.listarAprobacionActividad(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody AprobacionActividad AprobacionActividad) {
        return ResponseEntity.status(HttpStatus.OK).body(AprobacionActividadService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        AprobacionActividad a = AprobacionActividadService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(AprobacionActividadService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AprobacionActividad> actualizar(@PathVariable Long id, @RequestBody AprobacionActividad p) {

        try {
            AprobacionActividad a = AprobacionActividadService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setObservacion(p.getObservacion());
                a.setEstado(p.getEstado());
                a.setPoa(p.getPoa());
                a.setActividad(p.getActividad());
                a.setUsuario(p.getUsuario());
                return new ResponseEntity<>(AprobacionActividadService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Solicitar")
    public ResponseEntity<AprobacionActividad> Solicitar(@RequestParam("id_usuario") Long id_usuario, @RequestParam("id_actividad") Long id_actividad,@RequestParam("id_poa") Long id_poa) {
        AprobacionActividad a = new AprobacionActividad();
        Poa p = new Poa();
        Actividades ac = new Actividades();
        Usuario u = new Usuario();
        u.setId(id_usuario);
        ac.setId_actividad(id_actividad);
        p.setId_poa(id_poa);
        a.setUsuario(u);
        a.setActividad(ac);
        a.setPoa(p);
        a.setEstado("PENDIENTE");
        a.setObservacion("");
        a.setVisible(true);
        try {
            a.setVisible(true);
            return new ResponseEntity<>(AprobacionActividadService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarAprobacionesporActividad/{id_actividad}")
    public ResponseEntity<List<AprobacionporActividadProjection>> listarAprobacionesporActividad(@PathVariable("id_actividad") Long id_actividad){
        try {
            return new ResponseEntity<>(AprobacionActividadService.listarAprobacionesporActividad(id_actividad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
