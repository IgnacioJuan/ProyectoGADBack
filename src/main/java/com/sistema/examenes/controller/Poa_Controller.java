package com.sistema.examenes.controller;

import com.sistema.examenes.dto.PoaNoAprobadoDTO;
import com.sistema.examenes.dto.Poa_DTO;
import com.sistema.examenes.dto.PoaporUsuarioDTO;
import com.sistema.examenes.dto.SolicitudPoa;
import com.sistema.examenes.dto.PoasAdmin_DTO;
import com.sistema.examenes.entity.Poa;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.projection.PoaNoAprobadoProjection;
import com.sistema.examenes.projection.PoasConActividadesPendientesProjection;
import com.sistema.examenes.services.Poa_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/poa")
public class Poa_Controller {

    @Autowired
    Poa_Service Service;


    @PostMapping("/crear")
    public ResponseEntity<Poa> crear(@RequestBody Poa r) {
        try {
            r.setEstado("PENDIENTE");
            r.setVisible(true);        
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Poa>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarPoasDeModelo")
    public ResponseEntity<List<Poa_DTO>> listarPoasDeModelo() {
        List<Poa_DTO> poas = Service.listarPoasDeModelo();
        return ResponseEntity.ok(poas);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Poa> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @GetMapping("/buscarPorIds")
    public ResponseEntity<List<Poa>> buscarProyectosPorIds(@RequestParam List<Long> ids) {
        List<Poa> proyectos = Service.findByIds(ids);
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping("/findByIdAndVisibleTrue/{id}")
    public ResponseEntity<Object> getByIdVisibleTrue(@PathVariable("id") Long id) {
        try {
            Poa poa = Service.obtenerPoaId(id);
            if (poa != null && poa.isVisible()) {
                return ResponseEntity.ok(poa);
            } else if (poa != null && !poa.isVisible()) {
                String mensaje = "No existe en la base de datos.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
            } else {
                String mensaje = "Existe en la base de datos, pero no está activa.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Poa Poa) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Poa a = Service.findById(id);
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
    public ResponseEntity<Poa> actualizar(@PathVariable Long id, @RequestBody Poa p) {
        Poa a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setEstado(p.getEstado());
                a.setTipo_periodo(p.getTipo_periodo());
                a.setLocalizacion(p.getLocalizacion());
                a.setFecha_inicio(p.getFecha_fin());
                a.setFecha_fin(p.getFecha_fin());
                a.setCobertura(p.getCobertura());
                a.setBarrio(p.getBarrio());
                a.setComunidad(p.getComunidad());
                a.setLinea_base(p.getLinea_base());
                a.setMeta_planificada(p.getMeta_planificada());
                a.setMeta_alcanzar(p.getMeta_alcanzar());
                a.setUsuario(p.getUsuario());
                return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/listardelProyecto/{id_proyecto}/{estado}")
    public ResponseEntity<List<Poa>> listadelProyecto(@PathVariable Long id_proyecto, @PathVariable String estado) {
        try {
            return new ResponseEntity<>(Service.listarPoadelProyectoconEstado(id_proyecto, estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/noaprobados")
    public ResponseEntity<List<PoaNoAprobadoDTO>> getNoAprobados() {
        List<PoaNoAprobadoDTO> poaNoAprobados = Service.listarPoaNoAprobados();
        return new ResponseEntity<>(poaNoAprobados, HttpStatus.OK);
    }
    
    @GetMapping("/listarporusuario/{id_proyecto}")
    public ResponseEntity<List<PoaporUsuarioDTO>> getporUsuario(@PathVariable("id_proyecto") Long id_proyecto) {
        List<PoaporUsuarioDTO> poaporUsuario = Service.listarPoaporUsuarios(id_proyecto);
        return ResponseEntity.ok(poaporUsuario);
    }
    
    
   

    @PostMapping("/solicitud")
    public ResponseEntity<Poa> solicitud(@RequestBody SolicitudPoa r) {
        Poa poa = new Poa();
        try {
            poa.setMeta_planificada(r.getMeta_planificada());
            poa.setCobertura(r.getCobertura());
            poa.setBarrio(r.getBarrio());
            poa.setComunidad(r.getComunidad());
            poa.setLocalizacion(r.getLocalizacion());
            poa.setTipo_periodo(r.getTipo_periodo());
            poa.setLinea_base(0);
            poa.setMeta_alcanzar(0);
            poa.setEstado("PENDIENTE");
            poa.setVisible(true);
            return new ResponseEntity<>(Service.save(poa), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/listarPoasAdminEstado/{idResponsable}/{estado}")
    public ResponseEntity<List<PoasAdmin_DTO>> listarPoasPorAdminEstado(@PathVariable Long idResponsable, @PathVariable String estado) {
        try {
            return new ResponseEntity<>(Service.listarPoasPorAdminEstado(idResponsable, estado), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/PoasConActividadesP")
    public ResponseEntity<List<PoasConActividadesPendientesProjection>> PoasConActividadesPendientes() {
        try {
            return new ResponseEntity<>(Service.PoasConActividadesPendientes(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 
