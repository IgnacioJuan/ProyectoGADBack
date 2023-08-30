package com.sistema.examenes.controller;


import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.services.Eje_Service;
import com.sistema.examenes.services.Periodo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/periodo")
public class Periodo_Controller {
    @Autowired
    Periodo_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Periodo> crear(@RequestBody Periodo r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Periodo>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Periodo> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Periodo eje) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Periodo a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Periodo> actualizar(@PathVariable Long id, @RequestBody Periodo p) {
        Periodo a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setPorcentaje(p.getPorcentaje());
                a.setReferencia(p.getReferencia());
                a.setActividad(p.getActividad());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
