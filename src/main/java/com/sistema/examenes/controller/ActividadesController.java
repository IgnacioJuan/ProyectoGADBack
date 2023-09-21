package com.sistema.examenes.controller;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Periodo;
import com.sistema.examenes.entity.*;
import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.projection.ActividadesPendientesPorPoaProjection;
import com.sistema.examenes.projection.valorprojec;
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
//                a.setPoa(actividades.getPoa());
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
        try {
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
//            Periodo periodo = new Periodo();
            llenarPeriodos(valor_uno,actividad,1);
            llenarPeriodos(valor_dos,actividad,2);
            llenarPeriodos(valor_tres,actividad,3);
            llenarPeriodos(valor_cuatro,actividad,4);


//            periodo = new Periodo();
//            periodo.setPorcentaje(valor_dos);
//            periodo.setReferencia(2);
//            periodo.setVisible(true);
//            periodo.setActividad(actividad);
//            periodoService.save(periodo);
//
//            periodo = new Periodo();
//            periodo.setPorcentaje(valor_tres);
//            periodo.setReferencia(3);
//            periodo.setVisible(true);
//            periodo.setActividad(actividad);
//            periodoService.save(periodo);
//
//            periodo = new Periodo();
//            periodo.setPorcentaje(valor_cuatro);
//            periodo.setReferencia(4);
//            periodo.setVisible(true);
//            periodo.setActividad(actividad);
//            periodoService.save(periodo);

            System.out.println("Process finished with exit code 0");

            return new ResponseEntity<>(actividad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void llenarPeriodos(Double valor,Actividades actividad,int referencia) {
        Periodo periodo = new Periodo();
        periodo.setPorcentaje(valor);
        periodo.setReferencia(referencia);
        periodo.setVisible(true);
        periodo.setActividad(actividad);
        periodoService.save(periodo);
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
    @GetMapping("/valor/{id}")
    public ResponseEntity<valorprojec> getalor(@PathVariable("id") Long id) {
        try {
             return new ResponseEntity<>(actividadesService.valoracti(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/poaacti/{idres}/{idpoa}")
    public ResponseEntity<List<Actividades>> poaacti(
            @PathVariable("idres") Long idres,
            @PathVariable("idpoa") Long idpoa
    ) {
        List<Actividades> actividades = actividadesService.poaacti2(idres, idpoa);

        if (actividades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/fecha_fin/{id}")
    public ResponseEntity<Actividades> fecha(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(actividadesService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarResponsable")
    public ResponseEntity<Actividades> actualizarResponsable(@RequestParam("id_actividad") Long id_actividad,
                                                              @RequestParam("id_responsable") Long id_responsable) {

        try {
            Actividades a = actividadesService.findById(id_actividad);
            Usuario usuario = new Usuario();
            usuario.setId(id_responsable);
            a.setUsuario(usuario);
            return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizarDevengado/{id}")
    public ResponseEntity<Actividades> actualizarDevengado(@PathVariable Long id, @RequestBody Actividades actividades) {

        try {
            Actividades a = actividadesService.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setDevengado(actividades.getDevengado());
                return new ResponseEntity<>(actividadesService.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
