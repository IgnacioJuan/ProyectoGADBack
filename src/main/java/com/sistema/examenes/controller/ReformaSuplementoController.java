package com.sistema.examenes.controller;

import com.sistema.examenes.dto.PresupuestoEActividadDTO;
import com.sistema.examenes.dto.ReformaSActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.ReformaSuplemento;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.ReformaSuplementoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reformasuplemento")
public class ReformaSuplementoController {

    @Autowired
    private ReformaSuplementoService rsService;
    @Autowired
    private ActividadesService actividadesService;
    @PostMapping("/crear")
    public ResponseEntity<ReformaSuplemento> crear(@RequestBody ReformaSuplemento rs) {
        try {
            rs.setVisible(true);
            rsService.save(rs);// Guardar
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
    public ResponseEntity<ReformaSuplemento> crear(@RequestBody ReformaSuplemento a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(ReformaSuplementoSevice.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    //get listar
    @GetMapping("/listar")
    public ResponseEntity<List<ReformaSuplemento>> listar(){
        try {
            return new ResponseEntity<>(rsService.listarReformaSuplemento(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReformaSuplemento> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(rsService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReformaSuplemento ReformaSuplemento) {
        return ResponseEntity.status(HttpStatus.OK).body(rsService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReformaSuplemento a = rsService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(rsService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReformaSuplemento> actualizar(@PathVariable Long id, @RequestBody ReformaSuplemento p) {

        try {
            ReformaSuplemento a = rsService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setFecha(p.getFecha());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(rsService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarRSActividades")
    public List<ReformaSActividadDTO> listarRSActividades() {
        return rsService.listarRSActividades();
    }
}
