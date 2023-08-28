package com.sistema.examenes.controller;

import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.services.PresupuestoExternoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/presupuestoexterno")
public class PresupuestoExternoController {

    @Autowired
    private PresupuestoExternoService presupuestoExternoService;

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<PresupuestoExterno> crear(@RequestBody PresupuestoExterno a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<PresupuestoExterno>> listar(){
        try {
            return new ResponseEntity<>(presupuestoExternoService.listarPresupuestoExterno(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody PresupuestoExterno PresupuestoExterno) {
        return ResponseEntity.status(HttpStatus.OK).body(presupuestoExternoService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        PresupuestoExterno a = presupuestoExternoService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PresupuestoExterno> actualizar(@PathVariable Long id, @RequestBody PresupuestoExterno p) {

        try {
            PresupuestoExterno a = presupuestoExternoService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setNombre_institucion(p.getNombre_institucion());
                a.setObservacion(p.getObservacion());
                a.setActividad(p.getActividad());

                return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
