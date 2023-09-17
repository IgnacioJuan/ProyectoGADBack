package com.sistema.examenes.controller;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Componente;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.entity.*;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.projection.ActividadesPendientesPorPoaProjection;
import com.sistema.examenes.services.ActividadesService;
import com.sistema.examenes.services.AprobacionActividadService;
import com.sistema.examenes.services.Periodo_Service;
import com.sistema.examenes.services.PresupuestoExternoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/actividades")
public class ActividadesController {

    @Autowired
    private ActividadesService actividadesService;

    @Autowired
    private AprobacionActividadService aprobacionActividadService;

    @Autowired
    private PresupuestoExternoService presupuestoExternoService;

    @Autowired
    private Periodo_Service periodoService;

    // post crear

    @PostMapping("/crear")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades a) {
        try {
            a.setVisible(true);
            return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Actividades>> listar() {
        try {
            return new ResponseEntity<>(actividadesService.listarActividades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //listar actividades que tengan archivos rechazados
    @GetMapping("/listarActEviRechazados")
    public ResponseEntity<List<Actividades>> obtenerListarechazado() {
        try {
            return new ResponseEntity<>(actividadesService.listarActiEviRechazados(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Actividades actividades) {
        return ResponseEntity.status(HttpStatus.OK).body(actividadesService.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        Actividades a = actividadesService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Actividades> actualizar(@PathVariable Long id, @RequestBody Actividades actividades) {

        try {
            Actividades a = actividadesService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setNombre(actividades.getNombre());
                a.setDescripcion(actividades.getDescripcion());
                a.setCodificado(actividades.getCodificado());
                a.setDevengado(actividades.getDevengado());
                a.setRecursos_propios(actividades.getRecursos_propios());
                a.setPresupuesto_referencial(actividades.getPresupuesto_referencial());
                a.setEstado(actividades.getEstado());
                a.setUsuario(actividades.getUsuario());
                a.setPoa(actividades.getPoa());
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarActividadesPoa/{poaId}")
    public List<Actividades> listarActividadesPorIdPoa(@PathVariable Long poaId) {
        return actividadesService.listarActividadesPorIdPoa(poaId);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Actividades> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(actividadesService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // no borrar john es del gad
    @GetMapping("/actiresponsable/{id_resp}")
    public List<Actividades> listarActividadesPorResponsable(@PathVariable Long id_resp) {
        return actividadesService.listarActividadeSPORresponsable(id_resp);
    }

    //ni mio miriam
    @GetMapping("/listarUsuariosActividadID/{actividadId}")
    public List<UsuarioActividadesDTO> listarUsuariosActividadID(@PathVariable Long actividadId) {
        return actividadesService.listarUsuariosActividadID(actividadId);
    }

    @GetMapping("/listarActividadesPorIdResponsable/{responsableId}")
    public List<ActividadDTO> listarActividadesPorIdResponsable(@PathVariable Long responsableId) {
        return actividadesService.listarActividadesPorIdResponsable(responsableId);
    }
    // endpoints para ver el numero de actividades y las actividades del usuario
    @GetMapping("/usuactividades")
    public ResponseEntity<List<UsuarioActividadDTO>> obtenerUsuariosConActividades() {

        try {
            return new ResponseEntity<>(actividadesService.obtenerUsuariosConActividades(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detactividades/{idUsuario}")
    public ResponseEntity<List<DetalleActividadDTO>> obtenerDetalleActividades(@PathVariable Long idUsuario) {
        try {
            return new ResponseEntity<>(actividadesService.obtenerDetalleActividades(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Modulo de aprobacion POA
    @GetMapping("/detactividadesaprobpoa/{id_poa}")
    public ResponseEntity<List<ActividadApPoaDTO>> obtenerDetalleActividadesAprob(@PathVariable Long id_poa) {
        try {
            return new ResponseEntity<>(actividadesService.obtenerListaActividadesAprobPoa(id_poa), HttpStatus.OK);
        } catch (Exception e) {
           // System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //mtodo post
    @PostMapping("/solicitud")
    public ResponseEntity<Actividades> crearActividad(@RequestParam("nombre") String nombre,
                                                      @RequestParam("institucion_beneficiaria") String institucion_beneficiaria,
                                                      @RequestParam("recursos_externos") Double recursos_externos,
                                                      @RequestParam("valor_uno") Double valor_uno,
                                                      @RequestParam("valor_dos") Double valor_dos,
                                                      @RequestParam("valor_tres") Double valor_tres,
                                                      @RequestParam("valor_cuatro") Double valor_cuatro,
                                                      @RequestParam("descripcion") String descripcion,
                                                      @RequestParam("id_poa") Long id_poa,
                                                      @RequestParam("id_superadmin") Long id_superadmin,
                                                      @RequestParam("recursos_propios") Double recursos_propios,
                                                      @RequestParam("presupuesto_referencial") Double presupuesto_referencial) {
            Date fechaInicio1 = new Date();
            Date fechaFin1 = new Date();
            Date fechaInicio2 = new Date();
            Date fechaFin2 = new Date();
            Date fechaInicio3 = new Date();
            Date fechaFin3 = new Date();
            Date fechaInicio4 = new Date();
            Date fechaFin4 = new Date();

            Date fechaInicio = new Date();
            Date fechaFin = new Date();

        try {

            if(valor_cuatro == 0.0){
                //fecha inicio1 1 de enero del ano actual
                LocalDateTime fechaIni1= LocalDateTime.of(LocalDate.now().getYear()+1, 1, 1, 0, 0, 0);
                fechaInicio1 = Date.from(fechaIni1.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin1 30 de abril del ano actual
                LocalDateTime fechaFin1_= LocalDateTime.of(LocalDate.now().getYear()+1, 4, 30, 0, 0, 0);
                fechaFin1 = Date.from(fechaFin1_.atZone(ZoneId.systemDefault()).toInstant());
                //fecha inicio2 1 de mayo del ano actual
                LocalDateTime fechaIni2= LocalDateTime.of(LocalDate.now().getYear()+1, 5, 1, 0, 0, 0);
                fechaInicio2 = Date.from(fechaIni2.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin2 31 de agosto del ano actual
                LocalDateTime fechaFin2_= LocalDateTime.of(LocalDate.now().getYear()+1, 8, 31, 0, 0, 0);
                fechaFin2 = Date.from(fechaFin2_.atZone(ZoneId.systemDefault()).toInstant());
                //fecha inicio3 1 de septiembre del ano actual
                LocalDateTime fechaIni3= LocalDateTime.of(LocalDate.now().getYear()+1, 9, 1, 0, 0, 0);
                fechaInicio3 = Date.from(fechaIni3.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin3 31 de diciembre del ano actual
                LocalDateTime fechaFin3_= LocalDateTime.of(LocalDate.now().getYear()+1, 12, 31, 0, 0, 0);
                fechaFin3 = Date.from(fechaFin3_.atZone(ZoneId.systemDefault()).toInstant());
            }else{
                //fecha inicio1 1 de enero del ano actual
                LocalDateTime fechaIni1= LocalDateTime.of(LocalDate.now().getYear()+1, 1, 1, 0, 0, 0);
                fechaInicio1 = Date.from(fechaIni1.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin1 31 de marzo del ano actual
                LocalDateTime fechaFin1_= LocalDateTime.of(LocalDate.now().getYear()+1, 3, 31, 0, 0, 0);
                fechaFin1 = Date.from(fechaFin1_.atZone(ZoneId.systemDefault()).toInstant());
                //fecha inicio2 1 de abril del ano actual
                LocalDateTime fechaIni2= LocalDateTime.of(LocalDate.now().getYear()+1, 4, 1, 0, 0, 0);
                fechaInicio2 = Date.from(fechaIni2.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin2 30 de junio del ano actual
                LocalDateTime fechaFin2_= LocalDateTime.of(LocalDate.now().getYear()+1, 6, 30, 0, 0, 0);
                fechaFin2 = Date.from(fechaFin2_.atZone(ZoneId.systemDefault()).toInstant());
                //fecha inicio3 1 de julio del ano actual
                LocalDateTime fechaIni3= LocalDateTime.of(LocalDate.now().getYear()+1, 7, 1, 0, 0, 0);
                fechaInicio3 = Date.from(fechaIni3.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin3 30 de septiembre del ano actual
                LocalDateTime fechaFin3_= LocalDateTime.of(LocalDate.now().getYear()+1, 9, 30, 0, 0, 0);
                fechaFin3 = Date.from(fechaFin3_.atZone(ZoneId.systemDefault()).toInstant());
                //fecha inicio4 1 de octubre del ano actual
                LocalDateTime fechaIni4= LocalDateTime.of(LocalDate.now().getYear()+1, 10, 1, 0, 0, 0);
                fechaInicio4 = Date.from(fechaIni4.atZone(ZoneId.systemDefault()).toInstant());
                //fecha fin4 31 de diciembre del ano actual
                LocalDateTime fechaFin4_= LocalDateTime.of(LocalDate.now().getYear()+1, 12, 31, 0, 0, 0);
                fechaFin4 = Date.from(fechaFin4_.atZone(ZoneId.systemDefault()).toInstant());
            }

            Actividades a = new Actividades();
            Poa poa = new Poa();
            Usuario usuario = new Usuario();
            poa.setId_poa(id_poa);
            usuario.setId(id_superadmin);
            //llenar los datos de la actividad
            a.setNombre(nombre);
            a.setDescripcion(descripcion);
            a.setRecursos_propios(recursos_propios);
            a.setPresupuesto_referencial(presupuesto_referencial);
            a.setEstado("PENDIENTE");
            a.setVisible(true);
            a.setCodificado(presupuesto_referencial);
            a.setDevengado(0);
            a.setPoa(poa);
            a.setUsuario(null);
            a.setVisible(true);
            a.setFecha_creacion(new Date());

            if(valor_uno>0.0){
                a.setFecha_inicio(fechaInicio1);
            }else {
                if (valor_dos > 0.0) {
                    a.setFecha_inicio(fechaInicio2);
                } else {
                    if (valor_tres > 0.0) {
                        a.setFecha_inicio(fechaInicio3);
                    } else {
                        if (valor_cuatro > 0.0) {
                            a.setFecha_inicio(fechaInicio4);
                        }
                    }
                }
            }
            //fecha fin
            if(valor_cuatro>0){
                a.setFecha_fin(fechaFin4);
            }else{
                if(valor_tres>0){
                    a.setFecha_fin(fechaFin3);
                }else{
                    if(valor_dos>0){
                        a.setFecha_fin(fechaFin2);
                    }else{
                        if(valor_uno>0){
                            a.setFecha_fin(fechaFin1);
                        }
                    }
                }
            }


            Actividades actividad = actividadesService.save(a);
            //llena los datos de la aprobacion
            AprobacionActividad aprobacionActividad = new AprobacionActividad();
            aprobacionActividad.setObservacion("Solicitud de creacion de actividad");
            aprobacionActividad.setEstado("PENDIENTE");
            aprobacionActividad.setUsuario(usuario);
            aprobacionActividad.setActividad(actividad);
            aprobacionActividad.setPoa(poa);
            aprobacionActividad.setVisible(true);
            aprobacionActividad.setFechaAprobacion(new Date());
            aprobacionActividad=aprobacionActividadService.save(aprobacionActividad);

            //llenar los datos de los presupuestos externos
            if(recursos_externos!=0){
                PresupuestoExterno presupuestoExterno = new PresupuestoExterno();
                presupuestoExterno.setActividad(actividad);
                presupuestoExterno.setValor(recursos_externos);
                presupuestoExterno.setNombre_institucion(institucion_beneficiaria);
                presupuestoExterno.setObservacion("");
                presupuestoExterno.setVisible(true);
                presupuestoExternoService.save(presupuestoExterno);
            }

            Periodo periodo = new Periodo();
            periodo.setPorcentaje(valor_uno);
            periodo.setReferencia(1);
            periodo.setVisible(true);
            periodo.setActividad(actividad);
            periodo.setFecha_inicio(fechaInicio1);
            periodo.setFecha_fin(fechaFin1);
            periodoService.save(periodo);

            periodo = new Periodo();
            periodo.setPorcentaje(valor_dos);
            periodo.setReferencia(2);
            periodo.setVisible(true);
            periodo.setActividad(actividad);
            periodo.setFecha_inicio(fechaInicio2);
            periodo.setFecha_fin(fechaFin2);
            periodoService.save(periodo);

            periodo = new Periodo();
            periodo.setPorcentaje(valor_tres);
            periodo.setReferencia(3);
            periodo.setVisible(true);
            periodo.setActividad(actividad);
            periodo.setFecha_inicio(fechaInicio3);
            periodo.setFecha_fin(fechaFin3);
            periodoService.save(periodo);

            periodo = new Periodo();
            periodo.setPorcentaje(valor_cuatro);
            periodo.setReferencia(4);
            periodo.setVisible(true);
            periodo.setActividad(actividad);
            if(valor_cuatro>0){
                periodo.setFecha_inicio(fechaInicio4);
                periodo.setFecha_fin(fechaFin4);
            }
            periodoService.save(periodo);

            System.out.println("Process finished with exit code 0");

            return new ResponseEntity<>(actividad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarActividadesConTotalPresupuestos/{poaId}")
    public ResponseEntity<List<ListaActividadesPresupuestosDTO>> listarActividadesConTotales(@PathVariable Long poaId) {
        List<ListaActividadesPresupuestosDTO> actividades = actividadesService.listarActividadesConTotalPresupuestos(poaId);
        return ResponseEntity.ok(actividades);
    }

    @GetMapping("/ActividadesPendientesPorPoa/{id_Poa}")
    public List<ActividadesPendientesPorPoaProjection> ActividadesPendientesPorPoa(@PathVariable Long id_Poa) {
        return actividadesService.ActividadesPendientesPorPoa(id_Poa);
    }
}
