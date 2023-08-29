package com.sistema.examenes.controller;

import com.sistema.examenes.dto.Componente_DTO;
import com.sistema.examenes.entity.Componente;
import com.sistema.examenes.services.Componente_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/componente")
public class Componente_Controller {
    @Autowired
    Componente_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Componente> crear(@RequestBody Componente r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Componente>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Componente> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByIdAndVisibleTrue/{id}")
    public ResponseEntity<Object> getByIdVisibleTrue(@PathVariable("id") Long id) {
        try {
            Componente componente = Service.obtenerComponenteId(id);
            if (componente != null && componente.isVisible()) {
                return ResponseEntity.ok(componente);
            } else if (componente != null && !componente.isVisible()) {
                String mensaje = "No existe en la base de datos.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
            } else {
                String mensaje = "Existe en la base de datos, pero no está activa.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Componente Componente) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Componente a = Service.findById(id);
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
    public ResponseEntity<Componente> actualizar(@PathVariable Long id, @RequestBody Componente p) {
        Componente a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setNombre(p.getNombre());
                a.setCodigo(p.getCodigo());
                a.setVisible(p.isVisible());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/buscarComponenteLike/{nombre}")
    public ResponseEntity<List<Componente_DTO>> buscarComponentesPorNombre(@RequestParam("nombre") String nombre) {
        List<Componente_DTO> componentesEncontrados = Service.buscarComponentesPorNombre(nombre);
        return ResponseEntity.ok(componentesEncontrados);
    }
}
