package com.sistema.examenes.controller;

import com.sistema.examenes.entity.DetalleTrimestre;
import com.sistema.examenes.services.DetalleTrimestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/detalletrimestre")
public class DetalleTrimestreController {

    @Autowired
    private DetalleTrimestreService detalleTrimestreService;

    @PostMapping("/crear")
    public ResponseEntity<DetalleTrimestre> crear(@RequestBody DetalleTrimestre dt){
        try {
            dt.setVisible(true);
            return new ResponseEntity<>(detalleTrimestreService.save(dt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DetalleTrimestre>> listar(){
        try {
            return new ResponseEntity<>(detalleTrimestreService.listarDetalleTrimestres(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody DetalleTrimestre dt){
        return ResponseEntity.status(HttpStatus.OK).body(detalleTrimestreService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id){
        DetalleTrimestre dt = detalleTrimestreService.findById(id);
        if (dt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                dt.setVisible(false);
                return new ResponseEntity<>(detalleTrimestreService.save(dt), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DetalleTrimestre> actualizar(@PathVariable Long id, @RequestBody DetalleTrimestre dt){
        try {
            DetalleTrimestre dtActual = detalleTrimestreService.findById(id);
            if (dtActual != null) {
                dtActual.setPorcentaje(dt.getPorcentaje());
                dtActual.setPresupuesto_referencial(dt.getPresupuesto_referencial());
                dtActual.setEjecucion_presupuesto(dt.getEjecucion_presupuesto());
                return new ResponseEntity<>(detalleTrimestreService.save(dtActual), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}