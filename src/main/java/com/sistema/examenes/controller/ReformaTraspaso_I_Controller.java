package com.sistema.examenes.controller;

import com.sistema.examenes.entity.ReformaTraspaso_I;
import com.sistema.examenes.services.ReformaTraspaso_IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reformatraspaso_i")
public class ReformaTraspaso_I_Controller {

    @Autowired
    private ReformaTraspaso_IService reformaTraspaso_IService;

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<ReformaTraspaso_I> crear(@RequestBody ReformaTraspaso_I a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(reformaTraspaso_IService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<ReformaTraspaso_I>> listar(){
        try {
            return new ResponseEntity<>(reformaTraspaso_IService.listarReformasTraspaso_I(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReformaTraspaso_I ReformaTraspaso_I) {
        return ResponseEntity.status(HttpStatus.OK).body(reformaTraspaso_IService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReformaTraspaso_I a = reformaTraspaso_IService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(reformaTraspaso_IService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReformaTraspaso_I> actualizar(@PathVariable Long id, @RequestBody ReformaTraspaso_I p) {

        try {
            ReformaTraspaso_I a = reformaTraspaso_IService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setFecha(p.getFecha());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(reformaTraspaso_IService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
