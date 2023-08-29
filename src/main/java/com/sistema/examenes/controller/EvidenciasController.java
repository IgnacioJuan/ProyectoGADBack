package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Evidencias;
import com.sistema.examenes.services.EvidenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/evidencias")
public class EvidenciasController {

    @Autowired
    private EvidenciasService evidenciasService;

    @PostMapping("/crear")
    public ResponseEntity<Evidencias> crear(@RequestBody Evidencias ev){
        try {
            ev.setVisible(true);
            return new ResponseEntity<>(evidenciasService.save(ev), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Evidencias>> listar(){
        try {
            return new ResponseEntity<>(evidenciasService.listarEvidencias(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Evidencias ev){
        return ResponseEntity.status(HttpStatus.OK).body(evidenciasService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id){
        Evidencias ev = evidenciasService.findById(id);
        if (ev == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                ev.setVisible(false);
                return new ResponseEntity<>(evidenciasService.save(ev), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Evidencias> actualizar(@PathVariable Long id, @RequestBody Evidencias ev){
        Evidencias evidencias = evidenciasService.findById(id);
        if (evidencias == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                evidencias.setNombre(ev.getNombre());
                evidencias.setDescripcion(ev.getDescripcion());
                evidencias.setEnlace(ev.getEnlace());
                return new ResponseEntity<>(evidenciasService.save(evidencias), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
