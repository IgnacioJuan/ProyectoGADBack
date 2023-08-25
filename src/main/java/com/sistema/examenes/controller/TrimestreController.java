package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Trimestre;
import com.sistema.examenes.services.TrimestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/trimestre")
public class TrimestreController {

    @Autowired
    private TrimestreService trimestreService;

    @PostMapping("/crear")
    public ResponseEntity<Trimestre> crear(@RequestBody Trimestre trimestre){
        try {
            trimestre.setVisible(true);
            return new ResponseEntity<>(trimestreService.save(trimestre), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Trimestre>> listar(){
        try {
            return new ResponseEntity<>(trimestreService.listarTrimestres(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Trimestre trimestre){
        return ResponseEntity.status(HttpStatus.OK).body(trimestreService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id){
        Trimestre t = trimestreService.findById(id);
        if (t == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                t.setVisible(false);
                return new ResponseEntity<>(trimestreService.save(t), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Trimestre> actualizar(@PathVariable Long id, @RequestBody Trimestre trimestre){
        Trimestre t = trimestreService.findById(id);
        if (t == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                t.setNombre(trimestre.getNombre());
                return new ResponseEntity<>(trimestreService.save(t), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
