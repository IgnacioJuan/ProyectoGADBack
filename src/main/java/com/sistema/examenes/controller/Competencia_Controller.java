package com.sistema.examenes.controller;

import com.sistema.examenes.dto.Competencia_DTO;
import com.sistema.examenes.dto.Componente_DTO;
import com.sistema.examenes.entity.Competencia;
import com.sistema.examenes.services.Competencia_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/competencia")
public class Competencia_Controller {
    @Autowired
    Competencia_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Competencia> crear(@RequestBody Competencia r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Competencia>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listado")
    public ResponseEntity<List<Competencia_DTO>> listarCompetencias() {
        List<Competencia_DTO> competencias = Service.listado();
        return ResponseEntity.ok(competencias);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Competencia> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByIdAndVisibleTrue/{id}")
    public ResponseEntity<Object> getByIdVisibleTrue(@PathVariable("id") Long id) {
        try {
            Competencia competencia = Service.obtenerCompetenciaId(id);
            if (competencia != null && competencia.isVisible()) {
                return ResponseEntity.ok(competencia);
            } else if (competencia != null && !competencia.isVisible()) {
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
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Competencia Competencia) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Competencia a = Service.findById(id);
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
    public ResponseEntity<Competencia> actualizar(@PathVariable Long id, @RequestBody Competencia p) {
        Competencia a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setNombre(p.getNombre());
                a.setDescripcion(p.getDescripcion());
                a.setVisible(p.isVisible());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @GetMapping("/buscarcompetencianombre/{nombre}")
    public ResponseEntity<List<Competencia_DTO>> buscarCompetenciasPorNombreDTO(@PathVariable("nombre") String nombre) {
        List<Competencia_DTO> competenciasEncontradas = Service.buscarCompetenciasPorNombreDTO(nombre);
        return ResponseEntity.ok(competenciasEncontradas);
    }

}
