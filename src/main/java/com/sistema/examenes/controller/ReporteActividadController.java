package com.sistema.examenes.controller;

import com.sistema.examenes.entity.ReporteActividad;
import com.sistema.examenes.services.ReporteActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reporteactividad")
public class ReporteActividadController {

    @Autowired
    private ReporteActividadService reporteActividadService;

    @PostMapping("/crear")
    public ResponseEntity<ReporteActividad> crear(ReporteActividad ra){
        try {
            ra.setVisible(true);
            return new ResponseEntity<>(reporteActividadService.save(ra), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ReporteActividad>> listar(){
        try {
            return new ResponseEntity<>(reporteActividadService.listarReporteActividades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReporteActividad ra){
        return reporteActividadService.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id){
        ReporteActividad ra = reporteActividadService.findById(id);
        if (ra == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                ra.setVisible(false);
                return new ResponseEntity<>(reporteActividadService.save(ra), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReporteActividad> actualizar(@PathVariable Long id, @RequestBody ReporteActividad reporteActividad){
        ReporteActividad ra = reporteActividadService.findById(id);
        if(ra == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            try {
                ra.setCodificado(reporteActividad.getCodificado());
                ra.setDevengado(reporteActividad.getDevengado());
                ra.setFecha(reporteActividad.getFecha());

                return new ResponseEntity<>(reporteActividadService.save(ra), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
