package com.sistema.examenes.controller;

import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Archivo;
import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.mensajes.Archivosmensajes;
import com.sistema.examenes.projection.ArchivoProjection;
import com.sistema.examenes.repository.Archivo_repository;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.Archivo_Service;
import com.sistema.examenes.services.Archivoservices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("archivo")
@AllArgsConstructor
public class Archivo_Controller {
    
    @Autowired
    Archivo_repository archivorepo;
    @Autowired
    Archivoservices servis;
    @Autowired
    Archivo_Service archivoservis;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ActividadesService actiservis;

    @PostMapping("/upload")
    public ResponseEntity<Archivosmensajes> upload(@RequestParam("file") MultipartFile[] files,
                                                   @RequestParam("descripcion") String describcion,
                                                   @RequestParam("valor") Double valor,
                                                   @RequestParam("id_evidencia") Long id_actividad) {
        String meNsaje = "";
        try {
            Actividades actividad = actiservis.findById(id_actividad);
            if (actividad == null) {
                meNsaje = "No se encontró la evidencia con id " + id_actividad;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Archivosmensajes(meNsaje));
            }
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                servis.guardar(file);
                fileNames.add(file.getOriginalFilename());
            });
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = ServletUriComponentsBuilder.fromHttpUrl(host)
                    .path("/archivo/").path(fileNames.get(0)).toUriString();

            // Subtract valor from actividad's value
            double actividadValor = actividad.getDevengado();
            actividadValor += valor;
            actividad.setDevengado(actividadValor);
            actiservis.save(actividad);

            archivoservis.save(new Archivo_s(fileNames.toString().join(",", fileNames), describcion, url.toString(), valor, true, actividad));
            meNsaje = "Se subieron correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new Archivosmensajes(meNsaje + "url:" + url));
        } catch (Exception e) {
            meNsaje = "Fallo al subir";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Archivosmensajes(meNsaje));
        }
    }

    @GetMapping("/listarv")
    public ResponseEntity<List<Archivo_s>> obtenerListav() {
        try {
            return new ResponseEntity<>(archivoservis.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //listar archivos rechazados
    @GetMapping("/listarrechazados")
    public ResponseEntity<List<Archivo_s>> obtenerListarechazado() {
        try {
            return new ResponseEntity<>(archivoservis.listararchirechazados(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //listar archivos rechazados
    @GetMapping("/listarrechazados/{idActi}")
    public ResponseEntity<List<Archivo_s>> listarActiEviRechazados(@PathVariable("idActi") Long idActividad) {
        try {
            return new ResponseEntity<>(archivoservis.listararchivoActividad(idActividad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //listar archivo enlace
    @GetMapping("/listararchivoenlace/{idArchi}")
    public ResponseEntity<Archivo_s> listarArchiEnlace(@PathVariable("idArchi") Long idArchivo) {
        try {
            Archivo_s archivo = archivoservis.obtenerEnlacePorId(idArchivo);
            if (archivo != null) {
                return new ResponseEntity<>(archivo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listararchi")
    public ResponseEntity<List<ArchivoProjection>> listaarchi() {
        try {
            return new ResponseEntity<>(archivoservis.listararchi(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Archivo>> getFiles() {
        List<Archivo> archivos = servis.lIstar().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(Archivo_Controller.class, "getFile"
                    , path.getFileName().toString()).build().toString();

            return new Archivo(filename, url);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(archivos);
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = servis.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION
                , "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/borrar/{filename:.+}")
    public ResponseEntity<Archivosmensajes> borrar(@PathVariable String filename) {
        String mensaje = "";
        try {
            mensaje = servis.borrar(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new Archivosmensajes(mensaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Archivosmensajes(mensaje));
        }
    }


    @GetMapping("/buscarev/{username}")
    public ResponseEntity<List<Archivo_s>> listararchi(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(archivoservis.listararchivouser(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscararchivo/{idActi}")
    public ResponseEntity<List<Archivo_s>> listararchiActividad(@PathVariable("idActi") Long idActividad) {
        try {
            return new ResponseEntity<>(archivoservis.listararchivoActividad(idActividad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Archivo_s as = archivoservis.findById(id);
        if (as == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                // Get the associated actividad
                Actividades actividad = as.getActividad();

                // Update the valor of actividad
                double valor = as.getValor();
                double actividadValor = actividad.getDevengado();
                actividadValor -= valor;
                actividad.setDevengado(actividadValor);

                // Mark the Archivo_s as not visible
                as.setVisible(false);

                // Save the changes to both Archivo_s and Actividades
                archivoservis.save(as);
                actiservis.save(actividad);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/editar/{archivoId}")
    public ResponseEntity<Archivosmensajes> editUpload(@PathVariable Long archivoId,
                                                       @RequestParam("descripcion") String descripcion,
                                                       @RequestParam("valor") Double valor,
                                                       @RequestParam("id_evidencia") Long id_actividad) {
        String meNsaje = "";
        try {
            Actividades actividad = actiservis.findById(id_actividad);
            if (actividad == null) {
                meNsaje = "No se encontró la evidencia con id " + id_actividad;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Archivosmensajes(meNsaje));
            }
            Archivo_s archivo = archivoservis.findById(archivoId);
            if (archivo == null) {
                meNsaje = "No se encontró el archivo con id " + archivoId;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Archivosmensajes(meNsaje));
            }
            double oldValue = archivo.getValor();
            archivo.setDescripcion(descripcion);
            archivo.setValor(valor);
            archivoservis.save(archivo);

            // Subtract the oldValue from actividad's value and add the new valor
            double actividadValor = actividad.getDevengado()
                    ;
            actividadValor += valor - oldValue;
            actividad.setDevengado(actividadValor);
            actiservis.save(actividad);

            meNsaje = "Se actualizó correctamente";
            return ResponseEntity.status(HttpStatus.OK).body(new Archivosmensajes(meNsaje));
        } catch (Exception e) {
            meNsaje = "Fallo al actualizar";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Archivosmensajes(meNsaje));
        }
    }





    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Archivo_s> actualizar(@PathVariable Long id, @RequestBody Archivo_s p) {

        try {
            Archivo_s a = archivoservis.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setEstado(p.getEstado());
                return new ResponseEntity<>(archivoservis.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



@GetMapping("/listarPorEstadoYFechaDesc/{estado}/{username}")
public ResponseEntity<List<Archivo_s>> listarArchivosPorEstadoYFechaDesc(
    @PathVariable("estado") String estado,
    @PathVariable("username") String username) {
    try {
        if ("SINUSERNAME".equals(username)) {
            username = "";
        }
        System.out.println(username);
        List<Archivo_s> archivos = archivoservis.listarArchivosPorEstadoYUsuarioOrdenadoPorFechaDesc(estado, username);
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



}


















