package com.sistema.examenes.controller;


import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.AsignacionesUsuarios;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.services.AsignacionesUsuariosService;
import com.sistema.examenes.services.Eje_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/asignacion_usuario")
public class AsignacionUsuario_Controller {
    @Autowired
    AsignacionesUsuariosService Service;

    @PostMapping("/crear")
    public ResponseEntity<AsignacionesUsuarios> crear(@RequestBody AsignacionesUsuarios r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AsignacionesUsuarios>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<AsignacionesUsuarios> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody AsignacionesUsuarios eje) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        AsignacionesUsuarios a = Service.findById(id);
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
    public ResponseEntity<AsignacionesUsuarios> actualizar(@PathVariable Long id, @RequestBody AsignacionesUsuarios p) {
        AsignacionesUsuarios a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setFecha_asignacion(p.getFecha_asignacion());
                a.setUsuario(p.getUsuario());
                a.setActividad(p.getActividad());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/listarAsignacionesUsuarios/{actividadId}")
    public List<UsuarioActividadesDTO> listarAsignacionesUsuarios(@PathVariable Long actividadId) {
        return Service.listarAsignacionesUsuarios(actividadId);
    }

}
