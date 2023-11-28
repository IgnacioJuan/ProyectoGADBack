package com.sistema.examenes.controller;


import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Programa;
import com.sistema.examenes.services.Programa_Service;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/programa")
public class Programa_Controller {
    @Autowired
    Programa_Service Service;

    @PostMapping("/crear")
    public ResponseEntity<Programa> crear(@RequestBody Programa r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Programa>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Programa> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Programa programa) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Programa a = Service.findById(id);
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
    public ResponseEntity<Programa> actualizar(@PathVariable Long id, @RequestBody Programa p) {
        Programa a = Service.findById(id);
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
    @GetMapping("/buscarprogramanombre/{nombre}")
    public ResponseEntity<List<Programa_DTO>> buscarProgramasPorNombreDTO(@PathVariable("nombre") String nombre) {
        List<Programa_DTO> programasEncontrados = Service.buscarProgramasPorNombreDTO(nombre);
        return ResponseEntity.ok(programasEncontrados);
    }
    @GetMapping("/reporteiprogramas")
    public List<ReportIPrograma> obtenerProgramasConPorcentaje() {
        return Service.obtenerReportIProgramas();
    }

    @GetMapping("/reporteipproyectos/{programaId}")
    public ResponseEntity<List<ReportIPProyecto>> obtenerReporteProyectosPorPrograma(@PathVariable Long programaId) {
        List<ReportIPProyecto> reportes = Service.obtenerReporteProyectosPorPrograma(programaId);
        return ResponseEntity.ok(reportes);
    }

    // Usamos el servicio para generar el reporte
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Configuración para permitir que el navegador visualice el PDF
        headers.add("Content-Disposition", "inline; filename=Reporte de inversion por programa.pdf");
        return ResponseEntity.ok().headers(headers).body(Service.exportPdf());
    }

    @GetMapping("/export-pdf-report-ipp/{programaId}")
    public ResponseEntity<byte[]> exportPdfReportIPProyecto(@PathVariable Long programaId) throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Configuración para permitir que el navegador visualice el PDF
        headers.add("Content-Disposition", "inline; filename=Reporte de inversion por proyectos.pdf");
        return ResponseEntity.ok().headers(headers).body(Service.exportPdfReportIPProyecto(programaId));
    }
}
