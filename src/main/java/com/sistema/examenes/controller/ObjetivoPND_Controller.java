package com.sistema.examenes.controller;

import com.sistema.examenes.dto.ObjetivoPnd_DTO;
import com.sistema.examenes.entity.ObjetivoPND;
import com.sistema.examenes.services.ObjetivoPND_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/objetivopnd")
public class ObjetivoPND_Controller {
    @Autowired
    ObjetivoPND_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<ObjetivoPND> crear(@RequestBody ObjetivoPND r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ObjetivoPND>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ObjetivoPND> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ObjetivoPND objetivopnd) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        ObjetivoPND a = Service.findById(id);
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
    public ResponseEntity<ObjetivoPND> actualizar(@PathVariable Long id, @RequestBody ObjetivoPND p) {
        ObjetivoPND a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setNombre(p.getNombre());
                a.setDescripcion(p.getDescripcion());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @GetMapping("/buscarobjetivopndsnombre/{nombre}")
    public ResponseEntity<List<ObjetivoPnd_DTO>> buscarObjetivosPNDSPorNombreDTO(@PathVariable("nombre") String nombre) {
        List<ObjetivoPnd_DTO> objetivosEncontrados = Service.buscarObjetivosPNDSPorNombreDTO(nombre);
        return ResponseEntity.ok(objetivosEncontrados);
    }

    @GetMapping("/listarporeje/{idEje}")
    public ResponseEntity<List<ObjetivoPnd_DTO>> listarObjetivosPorIdEjeDTO(@PathVariable("idEje") Long idEje) {
        List<ObjetivoPnd_DTO> objetivosEncontrados = Service.listarObjetivosPorIdEjeDTO(idEje);
        return ResponseEntity.ok(objetivosEncontrados);
    }

}
