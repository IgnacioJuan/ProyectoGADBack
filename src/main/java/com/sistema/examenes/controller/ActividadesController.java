package com.sistema.examenes.controller;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.dto.MetaPdot_DTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.services.ActividadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/actividades")
public class ActividadesController {

    @Autowired
    private ActividadesService actividadesService;

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades a){
        try {
            a.setVisible(true);
            //a.setEstado("aprobado");
            return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<Actividades>> listar(){
        try {
            return new ResponseEntity<>(actividadesService.listarActividades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Actividades actividades) {
        return ResponseEntity.status(HttpStatus.OK).body(actividadesService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        Actividades a = actividadesService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Actividades> actualizar(@PathVariable Long id, @RequestBody Actividades actividades) {

        try {
            Actividades a = actividadesService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setNombre(actividades.getNombre());
                a.setDescripcion(actividades.getDescripcion());
                a.setObservaciones(actividades.getObservaciones());
                a.setPresepuesto_referencial(actividades.getPresepuesto_referencial());
                a.setReforma_suplemento(actividades.getReforma_suplemento());
                a.setReforma_traspaso_d(actividades.getReforma_traspaso_d());
                a.setReforma_traspaso_i(actividades.getReforma_traspaso_i());
                a.setPresupuesto_externo(actividades.getPresupuesto_externo());
                a.setCodificado(actividades.getCodificado());
                a.setEjecutado(actividades.getEjecutado());
                a.setSaldo(actividades.getSaldo());
                a.setEstado(actividades.getEstado());
                a.setUsuario(actividades.getUsuario());
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarActividadesPoa/{poaId}")
    public List<ActividadDTO> listarActividadesPorIdPoa(@PathVariable Long poaId) {
        return actividadesService.listarActividadesPorIdPoa(poaId);
    }
}
