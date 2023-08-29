package com.sistema.examenes.controller;

import com.sistema.examenes.dto.ObjetivoPdot_DTO;
import com.sistema.examenes.entity.Objetivo_pdot;
import com.sistema.examenes.services.ObjetivoPdot_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/objetivopdot")
public class ObjetivoPdot_Controller {
    @Autowired
    ObjetivoPdot_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Objetivo_pdot> crear(@RequestBody Objetivo_pdot r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Objetivo_pdot>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listarObjetivosPdots(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Objetivo_pdot> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByIdAndVisibleTrue/{id}")
    public ResponseEntity<Object> getByIdVisibleTrue(@PathVariable("id") Long id) {
        try {
            Objetivo_pdot obj = Service.obtenerObjetivoPdotId(id);
            if (obj != null && obj.isVisible()) {
                return ResponseEntity.ok(obj);
            } else if (obj != null && !obj.isVisible()) {
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
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Objetivo_pdot objetivopnd) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Objetivo_pdot a = Service.findById(id);
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
    public ResponseEntity<Objetivo_pdot> actualizar(@PathVariable Long id, @RequestBody Objetivo_pdot p) {
        Objetivo_pdot a = Service.findById(id);
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

    @GetMapping("/buscarObjetivoPdotLike/{nombre}")
    public ResponseEntity<List<ObjetivoPdot_DTO>> buscarObjetivosPdotsPorNombre(@RequestParam("nombre") String nombre) {
        List<ObjetivoPdot_DTO> objetivosEncontrados = Service.buscarObjetivosPdotsPorNombre(nombre);
        return ResponseEntity.ok(objetivosEncontrados);
    }

    @GetMapping("/listaObjetivosPdots/{idComponente}")
    public List<ObjetivoPdot_DTO> listarObjetivosPdotsPorIdComponente(@PathVariable Long idComponente) {
        return Service.listarObjetivosPdotsDTOPorIdComponente(idComponente);
    }
}
