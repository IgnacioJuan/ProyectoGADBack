package com.sistema.examenes.controller;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.AprobacionPoa_DTO;
import com.sistema.examenes.entity.AprobacionPoa;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.AprobacionPoaService;
import com.sistema.examenes.services.Poa_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/aprobacionpoa")
public class AprobacionPoaController {

    @Autowired
    private AprobacionPoaService AprobacionPoaService;

    @Autowired
    private Poa_Service poa_Service;
    
    @Autowired
    private ActividadesService actividadesService;

    //post crear

    @PostMapping("/crear")
    public ResponseEntity<AprobacionPoa> crear(@RequestBody AprobacionPoa a) {
        try {
            a.setVisible(true);
            a.setEstado("PENDIENTE");
            return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar

    @GetMapping("/listar")
    public ResponseEntity<List<AprobacionPoa>> listar() {
        try {
            return new ResponseEntity<>(AprobacionPoaService.listarAprobacionPoa(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody AprobacionPoa AprobacionPoa) {
        return ResponseEntity.status(HttpStatus.OK).body(AprobacionPoaService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        AprobacionPoa a = AprobacionPoaService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AprobacionPoa> actualizar(@PathVariable Long id, @RequestBody AprobacionPoa p) {

        try {
            AprobacionPoa a = AprobacionPoaService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setEstado(p.getEstado());
                a.setObservacion(p.getObservacion());
                a.setUsuario(p.getUsuario());
                a.setProyecto(p.getProyecto());
                a.setPoa(p.getPoa());

                return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarAprobaciones")
    public ResponseEntity<List<AprobPoa_DTO>> obtenerAprobacionesPoa() {
        try {
            return new ResponseEntity<>(AprobacionPoaService.obtenerAprobacionesPoa(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obtenerAprobacionPorId/{idPoa}")
    public ResponseEntity<AprobPoa_DTO> obtenerAprobacionPoaPorId(@PathVariable Long idPoa) {
        try {
            AprobPoa_DTO aprobPoaDTO = AprobacionPoaService.obtenerAprobacionPoaPorId(idPoa);
            if (aprobPoaDTO != null) {
                return new ResponseEntity<>(aprobPoaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizarestadoaprob/{id_poa}")
    public ResponseEntity<AprobacionPoa> actualizarEstadoAprobacion(@PathVariable Long id_poa, @RequestBody AprobacionPoa p) {

        try {
            //Obtengo mi poa de la base
            Poa poa = poa_Service.obtenerPoaId(id_poa); 
            

            //Obtengo actividades de la base
            AprobacionPoa a = AprobacionPoaService.obtenerAprobacionPorIdPoa(id_poa);
            
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setEstado(p.getEstado());
                a.setObservacion(p.getObservacion());

                //Seteo el nuevo estado del poa con el estado de la aprobacion
                poa.setEstado(p.getEstado());
                poa_Service.save(poa);
                actividadesService.actualizarEstadoPorIdPoa(id_poa,p.getEstado());
                return new ResponseEntity<>(AprobacionPoaService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/solicitarAprobacion")
    public ResponseEntity<AprobacionPoa> solicitarAprobacion(@RequestParam("idPoa") Long idPoa, @RequestParam("idUsuario") Long idUsuario, @RequestParam("idProyecto") Long idProyecto) {
        AprobacionPoa aprobacionPoa = new AprobacionPoa();
        Proyecto proyecto = new Proyecto();
        Poa poa = new Poa();
        Usuario usuario = new Usuario();

        proyecto.setId_proyecto(idProyecto);
        poa.setId_poa(idPoa);
        usuario.setId(idUsuario);
        aprobacionPoa.setEstado("PENDIENTE");
        aprobacionPoa.setObservacion("");
        aprobacionPoa.setProyecto(proyecto);
        aprobacionPoa.setPoa(poa);
        aprobacionPoa.setUsuario(usuario);
        aprobacionPoa.setVisible(true);
        try {
            return new ResponseEntity<>(AprobacionPoaService.save(aprobacionPoa), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listaAprobacionPoaPorId/{idPoa}")
    public List<AprobacionPoa_DTO> listarAprobacionPoaPorIdPoa(@PathVariable Long idPoa) {
        return AprobacionPoaService.listarAprobacionPoaPorIdPoa(idPoa);
    }






}
