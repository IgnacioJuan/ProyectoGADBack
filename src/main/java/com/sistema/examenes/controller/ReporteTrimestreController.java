package com.sistema.examenes.controller;

import com.sistema.examenes.entity.ReporteTrimestre;
import com.sistema.examenes.services.ReporteTrimestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reporteTrimestre")
public class ReporteTrimestreController {

    @Autowired
    private ReporteTrimestreService reporteTrimestreService;

    @PostMapping("/crear")
    public ResponseEntity<ReporteTrimestre> crear(@RequestBody ReporteTrimestre rt){
        try {
            rt.setVisible(true);
            return new ResponseEntity<>(reporteTrimestreService.save(rt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<ReporteTrimestre>> listar(){
        try {
            return new ResponseEntity<>(reporteTrimestreService.listarReporteTrimestres(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReporteTrimestre reporteTrimestre) {
        return reporteTrimestreService.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReporteTrimestre rt = reporteTrimestreService.findById(id);
        if (rt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                rt.setVisible(false);
                return new ResponseEntity<>(reporteTrimestreService.save(rt), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReporteTrimestre> actalizar(@PathVariable Long id, @RequestBody ReporteTrimestre reporteTrimestre) {
        ReporteTrimestre rt = reporteTrimestreService.findById(id);
        if (rt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            try{
                rt.setEjecucion_presupuesto(reporteTrimestre.getEjecucion_presupuesto());
                return new ResponseEntity<>(reporteTrimestreService.save(rt), HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
