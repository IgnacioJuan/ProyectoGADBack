package com.sistema.examenes.controller;

import com.sistema.examenes.dto.PresupuestoEActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.PresupuestoExterno;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.PresupuestoExternoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/presupuestoexterno")
public class PresupuestoExternoController {

    @Autowired
    private PresupuestoExternoService presupuestoExternoService;

    @Autowired
    private ActividadesService actividadesService;

    @PostMapping("/crear")
    public ResponseEntity<PresupuestoExterno> crear(@RequestBody PresupuestoExterno presupuestoExterno) {
        try {
            presupuestoExterno.setVisible(true);
            presupuestoExternoService.save(presupuestoExterno);// Guardar el presupuesto externo
            Actividades actividad = presupuestoExterno.getActividad();// Obtener la actividad relacionada al presupuesto externo
            if (actividad != null) {
                Optional<Actividades> actividadOptional = actividadesService.findActividadById(actividad.getId_actividad());// Obtener la entidad Actividades directamente de la base de datos
                if (actividadOptional.isPresent()) {
                    Actividades actividadCompleta = actividadOptional.get();
                    // Actualizar solo el campo "codificado"
                    double nuevoCodificado = actividadCompleta.getCodificado() + presupuestoExterno.getValor();
                    //double nuevoPR = actividadCompleta.getPresupuesto_referencial() + presupuestoExterno.getValor();
                    actividadCompleta.setCodificado(nuevoCodificado);
                    //actividadCompleta.setPresupuesto_referencial(nuevoPR);
                    actividadesService.save(actividadCompleta); // Guardar la actividad actualizada
                }
            }
            return new ResponseEntity<>(presupuestoExterno, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //post crear

    /*@PostMapping("/crear")
    public ResponseEntity<PresupuestoExterno> crear(@RequestBody PresupuestoExterno a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<PresupuestoExterno>> listar(){
        try {
            return new ResponseEntity<>(presupuestoExternoService.listarPresupuestoExterno(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PresupuestoExterno> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(presupuestoExternoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody PresupuestoExterno PresupuestoExterno) {
        return ResponseEntity.status(HttpStatus.OK).body(presupuestoExternoService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        PresupuestoExterno a = presupuestoExternoService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PresupuestoExterno> actualizar(@PathVariable Long id, @RequestBody PresupuestoExterno p) {

        try {
            PresupuestoExterno a = presupuestoExternoService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setNombre_institucion(p.getNombre_institucion());
                a.setObservacion(p.getObservacion());
                a.setFecha(p.getFecha());
                return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarPEActividades/{actividadId}")
    public List<PresupuestoEActividadDTO> listarPEActividades(@PathVariable Long actividadId) {
        return presupuestoExternoService.listarPEActividades(actividadId);
    }
    @PostMapping("Solicitud")
    public ResponseEntity<PresupuestoExterno> crearSolicitud(@RequestParam("valor") double valor,
                                                             @RequestParam("id_actividad") Long id_actividad,
                                                             @RequestParam("nombre_institucion") String nombre_institucion,
                                                             @RequestParam("observacion") String observacion){
        PresupuestoExterno a = new PresupuestoExterno();
        Actividades actividad = new Actividades();
        actividad.setId_actividad(id_actividad);
        a.setValor(valor);
        a.setNombre_institucion(nombre_institucion);
        a.setObservacion(observacion);
        a.setActividad(actividad);
        a.setVisible(true);
        a.setFecha(new Date());
        try {
            a.setVisible(true);
            return new ResponseEntity<>(presupuestoExternoService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
