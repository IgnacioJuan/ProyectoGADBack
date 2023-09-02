package com.sistema.examenes.controller;

import com.sistema.examenes.dto.RTIncrementoActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.ReformaSuplemento;
import com.sistema.examenes.entity.ReformaTraspaso_I;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.ReformaTraspaso_IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reformatraspaso_i")
public class ReformaTraspaso_I_Controller {

    @Autowired
    private ReformaTraspaso_IService rtiService;
    @Autowired
    private ActividadesService actividadesService;
    @PostMapping("/crear")
    public ResponseEntity<ReformaTraspaso_I> crear(@RequestBody ReformaTraspaso_I rs) {
        try {
            rs.setVisible(true);
            rtiService.save(rs);// Guardar
            Actividades actividad = rs.getActividad();// Obtener la actividad relacionada
            if (actividad != null) {
                Optional<Actividades> actividadOptional = actividadesService.findActividadById(actividad.getId_actividad());// Obtener la entidad Actividades directamente de la base de datos
                if (actividadOptional.isPresent()) {
                    Actividades actividadCompleta = actividadOptional.get();
                    // Actualizar solo el campo "codificado"
                    double nuevoCodificado = actividadCompleta.getCodificado() + rs.getValor();
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
    public ResponseEntity<ReformaTraspaso_I> crear(@RequestBody ReformaTraspaso_I a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(reformaTraspaso_IService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<ReformaTraspaso_I>> listar(){
        try {
            return new ResponseEntity<>(rtiService.listarReformasTraspaso_I(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReformaTraspaso_I> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(rtiService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReformaTraspaso_I ReformaTraspaso_I) {
        return ResponseEntity.status(HttpStatus.OK).body(rtiService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReformaTraspaso_I a = rtiService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(rtiService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReformaTraspaso_I> actualizar(@PathVariable Long id, @RequestBody ReformaTraspaso_I p) {

        try {
            ReformaTraspaso_I a = rtiService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setFecha(p.getFecha());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(rtiService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarRTIActividades")
    public List<RTIncrementoActividadDTO> listarRTIActividades() {
        return rtiService.listarRTIActividades();
    }
}
