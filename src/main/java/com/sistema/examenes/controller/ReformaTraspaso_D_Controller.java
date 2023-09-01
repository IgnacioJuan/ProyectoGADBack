package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.ReformaTraspaso_D;
import com.sistema.examenes.services.ReformaTraspaso_DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reformatraspaso_d")
public class ReformaTraspaso_D_Controller {

    @Autowired
    private ReformaTraspaso_DService reformaTraspaso_DService;

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<ReformaTraspaso_D> crear(@RequestBody ReformaTraspaso_D a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(reformaTraspaso_DService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<ReformaTraspaso_D>> listar(){
        try {
            return new ResponseEntity<>(reformaTraspaso_DService.listarReformasTraspaso_D(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReformaTraspaso_D> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(reformaTraspaso_DService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReformaTraspaso_D ReformaTraspaso_D) {
        return ResponseEntity.status(HttpStatus.OK).body(reformaTraspaso_DService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReformaTraspaso_D a = reformaTraspaso_DService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(reformaTraspaso_DService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReformaTraspaso_D> actualizar(@PathVariable Long id, @RequestBody ReformaTraspaso_D p) {

        try {
            ReformaTraspaso_D a = reformaTraspaso_DService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setFecha(p.getFecha());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(reformaTraspaso_DService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
