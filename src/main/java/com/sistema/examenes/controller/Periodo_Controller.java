package com.sistema.examenes.controller;

import com.sistema.examenes.dto.Eje_DTO;
import com.sistema.examenes.dto.PeriodoTotalPOA_DTO;
import com.sistema.examenes.dto.Periodo_DTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Eje;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.Eje_Service;
import com.sistema.examenes.services.Periodo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/periodo")
public class Periodo_Controller {
    @Autowired
    Periodo_Service Service;
    @Autowired
    ActividadesService actividadesService;

    @PostMapping("/crear")
    public ResponseEntity<Periodo> crear(@RequestBody Periodo r) {
        try {
            r.setVisible(true);
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Periodo>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Periodo> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Periodo periodo) {
        return ResponseEntity.status(HttpStatus.OK).body(Service.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Periodo a = Service.findById(id);
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
    public ResponseEntity<Periodo> actualizar(@PathVariable Long id, @RequestBody Periodo p) {
        Periodo a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setPorcentaje(p.getPorcentaje());
                a.setReferencia(p.getReferencia());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    //LO DEJO POR SI (SE SUPONE Q VALE PERO DA ERROR 500 Y NO HAY RESPUESTA | me rendi :<
    @PutMapping("/actualizarPeriodos/{id_actividad}")
    public ResponseEntity<List<Periodo>> actualizarPeriodos(@PathVariable Long id_actividad, @RequestBody List<Periodo> periodos) {
        List<Periodo> periodosActualizados = new ArrayList<>();

        // Obtén la actividad a la que pertenecen los periodos
        Actividades actividad = actividadesService.findById(id_actividad);

        if (actividad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                for (Periodo periodo : periodos) {
                    if (!periodo.getActividad().equals(actividad)) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    // Actualiza el porcentaje del periodo
                    Periodo periodoExistente = Service.findById(periodo.getId_periodo());
                    if (periodoExistente != null) {
                        periodoExistente.setPorcentaje(periodo.getPorcentaje());
                        periodosActualizados.add(Service.save(periodoExistente));
                    }
                }
                return new ResponseEntity<>(periodosActualizados, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @PostMapping("/solicitud")
    public ResponseEntity<Periodo> solicitud(@RequestParam("value") double value,
            @RequestParam("id_actividad") Long id_actividad,
            @RequestParam("referencia") int referencia) {
        Periodo p = new Periodo();
        Actividades a = new Actividades();
        a.setId_actividad(id_actividad);
        p.setActividad(a);
        p.setPorcentaje(value);
        p.setReferencia(referencia);
        p.setVisible(true);
        try {
            return new ResponseEntity<>(Service.save(p), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/porcentajereferencia/{id_poa}")
    public ResponseEntity<List<Periodo_DTO>> obtenerPorcentajeYReferenciaPorPoa(
            @PathVariable("id_poa") Long id_poa) {
        try {
            List<Periodo_DTO> dtos = Service.obtenerPorcentajeYReferenciaPorPoa(id_poa);
            if (!dtos.isEmpty()) {
                return new ResponseEntity<>(dtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/totalespoa/{id_poa}")
    public ResponseEntity<PeriodoTotalPOA_DTO> obtenerTotalesPorPoa(
            @PathVariable("id_poa") Long idPoa) {
        try {
            PeriodoTotalPOA_DTO dto = Service.obtenerTotalesPorPoa(idPoa);
            if (dto != null) {
                return new ResponseEntity<>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarPeriodosPorActividad/{actividadId}")
    public ResponseEntity<List<Periodo>> listarPeriodosPorActividad(@PathVariable Long actividadId) {
        List<Periodo> periodos = Service.listarPeriodosPorActividad(actividadId);
        return ResponseEntity.ok(periodos);
    }

    @DeleteMapping("/eliminarPorActividad/{actividadId}")
    public ResponseEntity<Void> eliminarPeriodosPorActividad(@PathVariable Long actividadId) {
        return Service.eliminarPeriodosPorActividad(actividadId);
    }

}
