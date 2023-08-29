package com.sistema.examenes.controller;

import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.services.AprobacionPoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/aprobacionpoa")
public class AprobacionPoaController {

    @Autowired
    private AprobacionPoaService AprobacionPoaService;

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<AprobacionPoa> crear(@RequestBody AprobacionPoa a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<AprobacionPoa>> listar(){
        try {
            return new ResponseEntity<>(AprobacionPoaService.listarAprobacionPoa(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody AprobacionPoa AprobacionPoa) {
        return ResponseEntity.status(HttpStatus.OK).body(AprobacionPoaService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        AprobacionPoa a = AprobacionPoaService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AprobacionPoa> actualizar(@PathVariable Long id, @RequestBody AprobacionPoa p) {

        try {
            AprobacionPoa a = AprobacionPoaService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setEstado(p.getEstado());
                a.setObservacion(p.getObservacion());
                a.setUsuario(p.getUsuario());
                a.setProyecto(p.getProyecto());
                a.setPoa(p.getPoa());

                return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
