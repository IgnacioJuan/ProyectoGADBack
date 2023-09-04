package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.AprobacionActividad;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.services.AprobacionActividadService;
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

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<AprobacionActividad> crear(@RequestBody AprobacionActividad a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(AprobacionActividadService.save(a), HttpStatus.CREATED);
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
}
