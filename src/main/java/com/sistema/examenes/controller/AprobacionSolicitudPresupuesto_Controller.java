package com.sistema.examenes.controller;

import com.sistema.examenes.entity.AprobacionEvidencia;
import com.sistema.examenes.entity.Aprobacion_SolicitudPresupuesto;
import com.sistema.examenes.services.AprobacionSolicitudPresupuesto_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/aprobacionsolicitud")
public class AprobacionSolicitudPresupuesto_Controller {
    @Autowired
    AprobacionSolicitudPresupuesto_Service AprobacionSolicitudPresupuesto_Service;


    //post crear

    @PostMapping("/crear")
    public ResponseEntity<Aprobacion_SolicitudPresupuesto> crear(@RequestBody Aprobacion_SolicitudPresupuesto a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(AprobacionSolicitudPresupuesto_Service.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<Aprobacion_SolicitudPresupuesto>> listar(){
        try {
            return new ResponseEntity<>(AprobacionSolicitudPresupuesto_Service.listarAprobacionSolicitud(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody AprobacionEvidencia AprobacionActividad) {
        return ResponseEntity.status(HttpStatus.OK).body(AprobacionSolicitudPresupuesto_Service.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        Aprobacion_SolicitudPresupuesto a = AprobacionSolicitudPresupuesto_Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(AprobacionSolicitudPresupuesto_Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Aprobacion_SolicitudPresupuesto> actualizar(@PathVariable Long id, @RequestBody Aprobacion_SolicitudPresupuesto p) {

        try {
            Aprobacion_SolicitudPresupuesto a = AprobacionSolicitudPresupuesto_Service.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setObservacion(p.getObservacion());
                a.setEstado(p.getEstado());
                a.setUsuario(p.getUsuario());
                a.setSolicitud(p.getSolicitud());
                a.setSolicitud(p.getSolicitud());

                return new ResponseEntity<>(AprobacionSolicitudPresupuesto_Service.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
