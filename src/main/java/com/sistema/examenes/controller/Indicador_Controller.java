package com.sistema.examenes.controller;


import com.sistema.examenes.dto.Indicador_DTO;
import com.sistema.examenes.dto.MetaPdot_DTO;
import com.sistema.examenes.dto.ObjetivoPdot_DTO;
import com.sistema.examenes.entity.Indicador;
import com.sistema.examenes.projection.IndicadorComponenteProjection;
import com.sistema.examenes.services.Indicador_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/Indicador")
public class Indicador_Controller {
    @Autowired
    Indicador_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Indicador> crear(@RequestBody Indicador r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Indicador>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Indicador> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Indicador Indicador) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Indicador a = Service.findById(id);
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
    public ResponseEntity<Indicador> actualizar(@PathVariable Long id, @RequestBody Indicador p) {
        Indicador a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setNombre(p.getNombre());
                a.setDescripcion(p.getDescripcion());
                a.setTipo_evaluacion(p.getTipo_evaluacion());
                a.setMetapdot(p.getMetapdot());
                a.setVisible(p.isVisible());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/findByIdAndVisibleTrue/{id}")
    public ResponseEntity<Object> getByIdVisibleTrue(@PathVariable("id") Long id) {
        try {
            Indicador indicador = Service.obtenerIndicadorId(id);
            if (indicador != null && indicador.isVisible()) {
                return ResponseEntity.ok(indicador);
            } else if (indicador != null && !indicador.isVisible()) {
                String mensaje = "No existe en la base de datos.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
            } else {
                String mensaje = "Existe en la base de datos, pero no está activo.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/buscarIndicadorLike/{nombre}")
    public ResponseEntity<List<Indicador_DTO>> buscarComponentesPorNombre(@RequestParam("nombre") String nombre) {
        List<Indicador_DTO> indicadoresEncontrados = Service.buscarIndicadoresPorNombre(nombre);
        return ResponseEntity.ok(indicadoresEncontrados);
    }

    @GetMapping("/listaIndicadores/{idMetaPdot}")
    public List<Indicador_DTO> listarIndicadoresPorIdMetaPdot(@PathVariable Long idMetaPdot) {
        return Service.listarIndicadoresPorIdMetaPdot(idMetaPdot);
    }

    @GetMapping("/listaIndicadoresconComponente")
    public List<IndicadorComponenteProjection> listarIndicadoresconComponente() {
        return Service.listarIndicadoresconComponente();
    }
    
     @GetMapping("/porproyectos")
    public List<Indicador> listarIndicadoresPorProyectos(@RequestParam List<Long> idsProyectos) {
        return Service.listarIndicadoresPorProyectos(idsProyectos);
    }
   
}
