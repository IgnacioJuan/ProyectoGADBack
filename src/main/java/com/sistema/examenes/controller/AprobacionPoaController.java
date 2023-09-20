package com.sistema.examenes.controller;

import com.sistema.examenes.dto.AprobPoa_DTO;
import com.sistema.examenes.dto.AprobacionPoa_DTO;
import com.sistema.examenes.entity.*;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.projection.IsAprobadoProjection;
import com.sistema.examenes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    @Autowired
    private AprobacionActividadService aprobacionActividadService;

    @Autowired
    private Periodo_Service periodoService;




    // post crear

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

    // get listar

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

    @PostMapping("/solicitarAprobacion")
    public ResponseEntity<AprobacionPoa> solicitarAprobacion(@RequestParam("idPoa") Long idPoa,
            @RequestParam("idUsuario") Long idUsuario, @RequestParam("idProyecto") Long idProyecto) {
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

    /******* MODULO APROBACION POA ********/

    @GetMapping("/obtenerpoasaprb")
    public ResponseEntity<List<AprobPoa_DTO>> obtenerPoasCompletos() {
        try {
            return new ResponseEntity<>(AprobacionPoaService.obtenerPoasCompletos(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obtenerpoaprb/{idPoa}")
    public ResponseEntity<AprobPoa_DTO> obtenerAprobacionPoaPorId(@PathVariable Long idPoa) {
        try {
            AprobPoa_DTO aprobPoaDTO = AprobacionPoaService.obtenerPoaCompletoPorId(idPoa);
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

    @PostMapping("/crearnestadoaprob/{id_poa}")
    public ResponseEntity<AprobacionPoa> crearAprobacionNEstado(@PathVariable Long id_poa,
            @RequestBody AprobacionPoa aprobNue) {
        try {
            // Obtengo mi poa de la base
            Poa poa = poa_Service.obtenerPoaId(id_poa);
            // Obtener mi aprobacion de la base con ese idPOA
            AprobacionPoa aprobBd = AprobacionPoaService.obtenerAprobacionPorIdPoa(id_poa);
            if (aprobBd == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // Seteo el nuevo estado del poa con el estado de la aprobacion
                poa.setEstado(aprobNue.getEstado());
                poa_Service.save(poa);
                // Actualizar el estado de las actividades
                actividadesService.actualizarEstadoPorIdPoa(id_poa, aprobNue.getEstado());
                // Crear una nueva aprobacion con los datos de la aprobacion anterior y la nueva ingresada
                aprobNue.setPoa(aprobBd.getPoa());
                aprobNue.setProyecto(aprobBd.getProyecto());
                aprobNue.setUsuario(aprobBd.getUsuario());
                aprobNue.setVisible(true);
                System.out.println("Aprobacion nueva: " + aprobNue.toString());
                // Crear una nueva aprobacion de cada actividad 
                List<Actividades> mActividades = actividadesService.listarActividadesPorIdPoa(id_poa);
                for (Actividades actividad : mActividades) {
                    AprobacionActividad aprobacionActividad = new AprobacionActividad();
                    aprobacionActividad.setObservacion(aprobNue.getObservacion());
                    aprobacionActividad.setEstado(aprobNue.getEstado());
                    aprobacionActividad.setActividad(actividad);
                    aprobacionActividad.setVisible(true);
                    aprobacionActividad.setPoa(poa);
                    aprobacionActividadService.save(aprobacionActividad);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Aprobacion actividad: " + aprobacionActividad.getEstado());
                }
                
                return new ResponseEntity<>(AprobacionPoaService.save(aprobNue), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/AprobarSolicitud")
    public ResponseEntity<AprobacionPoa> AprobarSolicitud(@RequestParam("estado") String estado,
                                                          @RequestParam("idPoa") Long idPoa,
                                                          @RequestParam("idUsuario") Long idUsuario,
                                                          @RequestParam("observacion") String observacion){
        try {
            //Inicializacion de variables
            AprobacionPoa aprobacionPoa = new AprobacionPoa();
            Proyecto proyecto = new Proyecto();
            Poa poa = poa_Service.findById(idPoa);
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            proyecto.setId_proyecto(poa.getProyecto().getId_proyecto());

            //APROBACION Aprobacionpoa seteo de variables
            aprobacionPoa.setEstado(estado);
            aprobacionPoa.setObservacion(observacion);
            aprobacionPoa.setVisible(true);
            aprobacionPoa.setPoa(poa);
            aprobacionPoa.setProyecto(proyecto);
            aprobacionPoa.setUsuario(usuario);
            aprobacionPoa.setFecha_aprobacion(new Date());


            //FINALIZAR POA Y ACTIVIDADES EN CASO DE APROBACION
            if(estado.equals("APROBADO")){
                IsAprobadoProjection isAprobadoProjection = poa_Service.getIsAprobado(poa.getProyecto().getId_proyecto());
                if(isAprobadoProjection.getEstadoAprobado()){
                    Poa poaAprobado = poa_Service.findById(isAprobadoProjection.getIdPoaAprobado());
                    poaAprobado.setEstado("FINALIZADO");
                    poa_Service.save(poaAprobado);

                    AprobacionPoa aprobacionPoaAprobado = getAprobacionPoa(poaAprobado, proyecto, usuario);
                    AprobacionPoaService.save(aprobacionPoaAprobado);
                    List<Actividades> actividades = actividadesService.listarActividadesPorIdPoa(poaAprobado.getId_poa());
                    for (Actividades actividad : actividades) {
                        actividad.setEstado("FINALIZADO");
                        actividadesService.save(actividad);

                        AprobacionActividad aprobacionActividad = getAprobacionActividad(actividad, poaAprobado, usuario);
                        aprobacionActividadService.save(aprobacionActividad);
                    }
                }

                //Poa cambiar estado y agregar fechas
                //ahora del primer dia del ano
            }

            LocalDateTime fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 1, 1, 0, 0, 0);
            Date fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
            //ahora del ultimo dia del ano
            LocalDateTime fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 12, 31, 23, 59, 59);
            Date fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
            poa.setFecha_inicio(fechaInicioPoa);
            poa.setFecha_fin(fechaFinPoa);
            poa.setEstado(estado);
            poa_Service.save(poa);
            List<Actividades> actividades = actividadesService.listarActividadesPorIdPoa(idPoa);
            Date fechaInicioActividad= new Date();
            Date fechaFinActividad= new Date();

            Date fechaInicio1 = new Date(), fechaFin1= new Date(), fechaInicio2= new Date(), fechaFin2= new Date(), fechaInicio3= new Date(), fechaFin3= new Date(), fechaInicio4= new Date(), fechaFin4= new Date();

            for (Actividades actividad : actividades) {
                boolean bandera1 = false;
                boolean bandera2 = false;
                boolean bandera3 = false;
                boolean bandera4 = false;
                List<Periodo> periodos = periodoService.listarPeriodosPorActividad(actividad.getId_actividad());
                for (Periodo periodo : periodos) {
                    if(poa.getTipo_periodo().equals("TRIMESTRE")){
                        switch ((int) periodo.getReferencia()){
                            case 1:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 1, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 3, 31, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio1 = periodo.getFecha_inicio();
                                fechaFin1 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera1 = true;

                                }
                                break;
                            case 2:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 4, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 6, 30, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio2 = periodo.getFecha_inicio();
                                fechaFin2 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera2 = true;
                                    fechaInicioActividad = periodo.getFecha_inicio();
                                }
                                break;
                            case 3:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 7, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 9, 30, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio3 = periodo.getFecha_inicio();
                                fechaFin3 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera3 = true;
                                    fechaInicioActividad = periodo.getFecha_inicio();
                                }
                                break;
                            case 4:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 10, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 12, 31, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio4 = periodo.getFecha_inicio();
                                fechaFin4 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera4 = true;
                                    fechaInicioActividad = periodo.getFecha_inicio();
                                }
                                break;
                        }

                        if(bandera1){
                            fechaInicioActividad = fechaInicio1;
                        }else{
                            if(bandera2){
                                fechaInicioActividad = fechaInicio2;
                            }else{
                                if(bandera3){
                                    fechaInicioActividad = fechaInicio3;
                                }else{
                                    if(bandera4){
                                        fechaInicioActividad = fechaInicio4;
                                    }
                                }
                            }
                        }

                        if(bandera4) {
                            fechaFinActividad = fechaFin4;
                        }else{
                            if(bandera3){
                                fechaFinActividad = fechaFin3;
                            }else{
                                if(bandera2){
                                    fechaFinActividad = fechaFin2;
                                }else{
                                    if(bandera1){
                                        fechaFinActividad = fechaFin1;
                                    }
                                }
                            }
                        }
                    }else{
                        System.out.println((int) periodo.getReferencia());
                        switch ((int) periodo.getReferencia()){
                            case 1:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 1, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 4, 30, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio1 = periodo.getFecha_inicio();
                                fechaFin1 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera1 = true;
                                    fechaInicioActividad = periodo.getFecha_inicio();
                                }
                                break;
                            case 2:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 5, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 8, 31, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio2 = periodo.getFecha_inicio();
                                fechaFin2 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera2 = true;
                                    fechaInicioActividad = periodo.getFecha_inicio();
                                }
                                break;
                            case 3:
                                fechaInicio= LocalDateTime.of(LocalDate.now().getYear(), 9, 1, 0, 0, 0);
                                fechaInicioPoa = Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant());
                                //ahora del ultimo dia del ano
                                fechaFin= LocalDateTime.of(LocalDate.now().getYear(), 12, 31, 23, 59, 59);
                                fechaFinPoa = Date.from(fechaFin.atZone(ZoneId.systemDefault()).toInstant());
                                periodo.setFecha_inicio(fechaInicioPoa);
                                periodo.setFecha_fin(fechaFinPoa);
                                periodoService.save(periodo);
                                fechaInicio3 = periodo.getFecha_inicio();
                                fechaFin3 = periodo.getFecha_fin();
                                if(periodo.getPorcentaje()>0){
                                    bandera3 = true;
                                    fechaInicioActividad = periodo.getFecha_inicio();
                                }
                                break;
                        }
                        if(bandera1){
                            fechaInicioActividad = fechaInicio1;
                        }else{
                            if(bandera2){
                                fechaInicioActividad = fechaInicio2;
                            }else{
                                if(bandera3){
                                    fechaInicioActividad = fechaInicio3;
                                }
                            }
                        }
                        if(bandera3) {
                            fechaFinActividad = fechaFin3;
                        }else{
                            if(bandera2){
                                fechaFinActividad = fechaFin2;
                            }else{
                                if(bandera1){
                                    fechaFinActividad = fechaFin1;
                                }
                            }
                        }
                    }
                }


                actividad.setEstado(estado);
                actividad.setFecha_inicio(fechaInicioActividad);
                actividad.setFecha_fin(fechaFinActividad);
                actividadesService.save(actividad);

                AprobacionActividad aprobacionActividad=new AprobacionActividad();
                aprobacionActividad.setEstado(estado);
                aprobacionActividad.setObservacion("ACTIVIDAD "+estado);
                aprobacionActividad.setVisible(true);
                aprobacionActividad.setActividad(actividad);
                aprobacionActividad.setPoa(poa);
                aprobacionActividad.setUsuario(usuario);
                aprobacionActividad.setFechaAprobacion(new Date());
                aprobacionActividadService.save(aprobacionActividad);
            }



            return new ResponseEntity<>(AprobacionPoaService.save(aprobacionPoa), HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static AprobacionActividad getAprobacionActividad(Actividades actividad, Poa poaAprobado, Usuario usuario) {
        AprobacionActividad aprobacionActividad = new AprobacionActividad();
        aprobacionActividad.setEstado("FINALIZADO");
        aprobacionActividad.setObservacion("ACTIVIDAD FINALIZADA");
        aprobacionActividad.setVisible(true);
        aprobacionActividad.setActividad(actividad);
        aprobacionActividad.setPoa(poaAprobado);
        aprobacionActividad.setFechaAprobacion(new Date());
        aprobacionActividad.setUsuario(usuario);
        return aprobacionActividad;
    }

    private static AprobacionPoa getAprobacionPoa(Poa poaAprobado, Proyecto proyecto, Usuario usuario) {
        AprobacionPoa aprobacionPoaAprobado = new AprobacionPoa();
        aprobacionPoaAprobado.setEstado("FINALIZADO");
        aprobacionPoaAprobado.setObservacion("POA FINALIZADO");
        aprobacionPoaAprobado.setVisible(true);
        aprobacionPoaAprobado.setPoa(poaAprobado);
        aprobacionPoaAprobado.setProyecto(proyecto);
        aprobacionPoaAprobado.setUsuario(usuario);
        aprobacionPoaAprobado.setFecha_aprobacion(new Date());
        return aprobacionPoaAprobado;
    }
    
    //AprobacionActividad aprobacionActividad


    //    List<IsAprobadoProjection> isAprobadoProjection = poa_Service.getIsAprobado(poa.getProyecto().getId_proyecto());
 //crear metodo get para ese servicio
    @GetMapping("/getIsAprobado/{idProyecto}")
    public ResponseEntity<IsAprobadoProjection> getIsAprobado(@PathVariable Long idProyecto){
        try {
            IsAprobadoProjection isAprobadoProjection = poa_Service.getIsAprobado(idProyecto);
            return new ResponseEntity<>(isAprobadoProjection, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
