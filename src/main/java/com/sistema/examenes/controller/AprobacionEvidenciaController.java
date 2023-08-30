package com.sistema.examenes.controller;

import com.sistema.examenes.entity.AprobacionActividad;
import com.sistema.examenes.entity.AprobacionEvidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sistema.examenes.services.AprobacionEvidenciaService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/aprobacionevidencia")
public class AprobacionEvidenciaController {
    @Autowired
    AprobacionEvidenciaService AprobacionEvidenciaService;


    //post crear

    @PostMapping("/crear")
    public ResponseEntity<AprobacionEvidencia> crear(@RequestBody AprobacionEvidencia a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(AprobacionEvidenciaService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<AprobacionEvidencia>> listar(){
        try {
            return new ResponseEntity<>(AprobacionEvidenciaService.listarAprobacionEvidencia(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody AprobacionEvidencia AprobacionActividad) {
        return ResponseEntity.status(HttpStatus.OK).body(AprobacionEvidenciaService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        AprobacionEvidencia a = AprobacionEvidenciaService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(AprobacionEvidenciaService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AprobacionEvidencia> actualizar(@PathVariable Long id, @RequestBody AprobacionEvidencia p) {

        try {
            AprobacionEvidencia a = AprobacionEvidenciaService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setObservacion(p.getObservacion());
                a.setEstado(p.getEstado());
                a.setUsuario(p.getUsuario());
                a.setEvidencia(p.getEvidencia());
                a.setEstado(p.getEstado());
                return new ResponseEntity<>(AprobacionEvidenciaService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
