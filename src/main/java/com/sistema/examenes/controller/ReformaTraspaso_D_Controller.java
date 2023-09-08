package com.sistema.examenes.controller;

import com.sistema.examenes.dto.RTDecrementoActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.ReformaTraspaso_D;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.ReformaTraspaso_DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reformatraspaso_d")
public class ReformaTraspaso_D_Controller {

    @Autowired
    private ReformaTraspaso_DService rtdService;
    @Autowired
    private ActividadesService actividadesService;
    @PostMapping("/crear")
    public ResponseEntity<ReformaTraspaso_D> crear(@RequestBody ReformaTraspaso_D rs) {
        try {
            rs.setVisible(true);
            rtdService.save(rs);// Guardar
            Actividades actividad = rs.getActividad();// Obtener la actividad relacionada
            if (actividad != null) {
                Optional<Actividades> actividadOptional = actividadesService.findActividadById(actividad.getId_actividad());// Obtener la entidad Actividades directamente de la base de datos
                if (actividadOptional.isPresent()) {
                    Actividades actividadCompleta = actividadOptional.get();
                    // Actualizar solo el campo "codificado"
                    double nuevoCodificado = actividadCompleta.getCodificado() - rs.getValor();
                    actividadCompleta.setCodificado(nuevoCodificado);
                    actividadesService.save(actividadCompleta); // Guardar la actividad actualizada
                }
            }
            return new ResponseEntity<>(rs, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*@PostMapping("/crear")
    public ResponseEntity<ReformaTraspaso_D> crear(@RequestBody ReformaTraspaso_D a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(reformaTraspaso_DService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @GetMapping("/listar")
    public ResponseEntity<List<ReformaTraspaso_D>> listar(){
        try {
            return new ResponseEntity<>(rtdService.listarReformasTraspaso_D(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReformaTraspaso_D> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(rtdService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReformaTraspaso_D ReformaTraspaso_D) {
        return ResponseEntity.status(HttpStatus.OK).body(rtdService.delete(id));
    }
    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReformaTraspaso_D a = rtdService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(rtdService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReformaTraspaso_D> actualizar(@PathVariable Long id, @RequestBody ReformaTraspaso_D p) {

        try {
            ReformaTraspaso_D a = rtdService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setFecha(p.getFecha());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(rtdService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarRTDActividades/{actividadId}")
    public List<RTDecrementoActividadDTO> listarRTDActividades(@PathVariable Long actividadId) {
        return rtdService.listarRTDActividades(actividadId);
    }
}
