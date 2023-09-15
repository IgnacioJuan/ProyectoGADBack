package com.sistema.examenes.controller;


import com.sistema.examenes.dto.SolicitudPresupuesto_DTO;
import com.sistema.examenes.entity.SolicitudPresupuesto;
import com.sistema.examenes.services.SolicitudPresupuesto_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/solicitudpresupuesto")
public class SolicitudPresupuestoController {
    @Autowired
    SolicitudPresupuesto_Service SolicitudPresupuesto_Service ;


    //post crear

    @PostMapping("/crear")
    public ResponseEntity<SolicitudPresupuesto> crear(@RequestBody SolicitudPresupuesto a){
        try {
            a.setEstado("PENDIENTE");
            a.setVisible(true);
            return new ResponseEntity<>(SolicitudPresupuesto_Service.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<SolicitudPresupuesto>> listarSolicitudPresupuesto(){
        try {
            return new ResponseEntity<>(SolicitudPresupuesto_Service.listarSolicitudPresupuesto(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody SolicitudPresupuesto SolicitudPresupuesto) {
        return ResponseEntity.status(HttpStatus.OK).body(SolicitudPresupuesto_Service.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        SolicitudPresupuesto a = SolicitudPresupuesto_Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(SolicitudPresupuesto_Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<SolicitudPresupuesto> actualizar(@PathVariable Long id, @RequestBody SolicitudPresupuesto p) {

        try {
            SolicitudPresupuesto a = SolicitudPresupuesto_Service.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setMotivo(p.getMotivo());
                a.setMonto_actual(p.getMonto_actual());
                a.setReforma(p.getReforma());
                a.setMonto_total(p.getMonto_total());
                a.setEstado(p.getEstado());
                a.setFecha_solicitud(p.getFecha_solicitud());
                return new ResponseEntity<>(SolicitudPresupuesto_Service.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarSolicitudesResponsableEstado/{idResponsable}/{estado}")
    public ResponseEntity<List<SolicitudPresupuesto_DTO>> listarPoasPorResponsableEstado(@PathVariable Long idResponsable, @PathVariable String estado) {
        try {
            return new ResponseEntity<>(SolicitudPresupuesto_Service.listarPoasPorResponsableEstado(idResponsable, estado), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarSolicitudesSuperAdmin/{idSuperAdmin}")
    public ResponseEntity<List<SolicitudPresupuesto>> listarPoasPorResponsableEstado(@PathVariable Long idSuperAdmin) {
        try {
            return new ResponseEntity<>(SolicitudPresupuesto_Service.listarSolicitudPresupuestoSuperAdmin(idSuperAdmin), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
