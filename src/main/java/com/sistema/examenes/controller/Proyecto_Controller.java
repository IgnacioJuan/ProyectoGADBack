package com.sistema.examenes.controller;


import com.sistema.examenes.dto.ProjectByIdDto;
import com.sistema.examenes.dto.ProjectsActivesDto;
import com.sistema.examenes.dto.ProyectoExportarexcelDTO;
import com.sistema.examenes.dto.ProyectoResumenDTO;
import com.sistema.examenes.entity.Proyecto;
import com.sistema.examenes.services.Proyecto_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/proyecto")
public class Proyecto_Controller {
    @Autowired
    Proyecto_Service Service;

    @PostMapping("/crear/{codigo}")
    public ResponseEntity<Proyecto> crear(@RequestBody Proyecto r, @PathVariable("codigo") String codigo) {
        try {
            String secuencia = "";
            r.setVisible(true);
            if (Service.secuenciaproyecto(codigo) == null){
                secuencia = "001";
            }else {
                Long secuenciaNumerica = Service.secuenciaproyecto(codigo);
                secuencia = String.format("%03d", secuenciaNumerica);
            }
            if(!r.getCodigo().endsWith("-")){
                r.setCodigo(r.getCodigo() + "-");
            }
            r.setCodigo(r.getCodigo() + secuencia);

            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proyecto>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/buscar/{ids}")
public ResponseEntity<List<Proyecto>> getByIds(@PathVariable("ids") List<Long> ids) {
    try {
        List<Proyecto> proyectos = Service.findByIds(ids);
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
 @GetMapping("/buscarPorIds")
    public ResponseEntity<List<Proyecto>> buscarProyectosPorIds(@RequestParam List<Long> ids) {
        List<Proyecto> proyectos = Service.findByIds(ids);
        return ResponseEntity.ok(proyectos);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Proyecto Proyecto) {
        return Service.delete(id);
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Proyecto a = Service.findById(id);
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
    public ResponseEntity<Proyecto> actualizar(@PathVariable Long id, @RequestBody Proyecto p) {
        Proyecto a = Service.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setCodigo(p.getCodigo());
                a.setNombre(p.getNombre());
                a.setObjetivo(p.getObjetivo());
                a.setMeta(p.getMeta());
                a.setPorcentaje_alcance(p.getPorcentaje_alcance());
                a.setOds(p.getOds());
                a.setPnd(p.getPnd());
                a.setModelopoa(p.getModelopoa());
                a.setPrograma(p.getPrograma());
                a.setIndicador(p.getIndicador());
                a.setCompetencia(p.getCompetencia());
                a.setDescripcion(p.getDescripcion());
                a.setFecha_inicio(p.getFecha_inicio());
                a.setFecha_fin(p.getFecha_fin());
                a.setVisible(p.isVisible());
                 return new ResponseEntity<>(Service.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/listardelModelo/{id_modelo_poa}")
    public ResponseEntity<List<Proyecto>> ListadelModelo(@PathVariable("id_modelo_poa") Long id_modelo_poa) {
        try {
            return new ResponseEntity<>(Service.listardelModelo(id_modelo_poa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @GetMapping("/exportarexcel/{id_modelo_poa}")
    public ResponseEntity<List<ProyectoExportarexcelDTO>> Exportarexcel(@PathVariable("id_modelo_poa") Long id_modelo_poa) {
        List<ProyectoExportarexcelDTO> exportar = Service.exportarexcel(id_modelo_poa);
        return ResponseEntity.ok(exportar);
    }    

    
    @GetMapping("/listsActiveProjects")
    public ResponseEntity<List<ProjectsActivesDto>> listActiveProjects( @RequestParam("id_usuario") Long id_usuario) {
        List<ProjectsActivesDto> projectsActivesDtoList = Service.listActiveProjects(id_usuario);
        return ResponseEntity.ok(projectsActivesDtoList);
    }

    @GetMapping("/getProject")
    public ResponseEntity<List<ProjectByIdDto>> getProjectById(@RequestParam Long id_proyecto) {
        List<ProjectByIdDto> projectById = Service.ProjectById(id_proyecto);
        return ResponseEntity.ok(projectById);
    }
}
