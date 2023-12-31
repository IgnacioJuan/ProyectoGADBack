package com.sistema.examenes.services;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.projection.ActividadesPendientesPorPoaProjection;
import com.sistema.examenes.projection.activ_fecha_lim_projection;
import com.sistema.examenes.projection.actividad_archi_projection;
import com.sistema.examenes.projection.valorprojec;
import com.sistema.examenes.repository.ActividadesRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadesServiceImpl extends GenericServiceImpl<Actividades, Long> implements ActividadesService {

    @Autowired
    ActividadesRepository actividadesRepository;

    @Override
    public CrudRepository<Actividades, Long> getDao() {
        return actividadesRepository;
    }

    @Override
    public Optional<Actividades> findActividadById(Long id) {
        return actividadesRepository.findById(id);
    }

    @Override
    public List<Actividades> listarActividades() {
        return actividadesRepository.listarActividades();
    }

   /* @Override
    public List<ActividadDTO> listarActividadesPorIdPoa(Long poaId) {
        List<Object[]> resultados = actividadesRepository.listarActividadesPorIdPoa(poaId);
        List<ActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ActividadDTO m = new ActividadDTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setNombre((String) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setPresupuesto_referencial((Double) resultado[3]);
            m.setRecursos_propios((Double) resultado[4]);
            m.setCodificado((Double) resultado[5]);
            m.setDevengado((Double) resultado[6]);
            m.setEstado((String) resultado[7]);
            // m.setResponsable((String) resultado[8] );
            acts.add(m);
        }
        return acts;
    }*/

    @Override
    public List<ActividadDTO> listarActividadesPorIdPoa(Long poaId) {
        List<Object[]> resultados = actividadesRepository.listarActividadesPorIdPoa(poaId);
        List<ActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ActividadDTO m = new ActividadDTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setNombre((String) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setPresupuesto_referencial((Double) resultado[3]);
            m.setRecursos_propios((Double) resultado[4]);
            m.setCodificado((Double) resultado[5]);
            m.setDevengado((Double) resultado[6]);
            m.setEstado((String) resultado[7]);
            acts.add(m);
        }
        return acts;
    }

    @Override
    public List<ActividadDTO> listarActividades2() {
        List<Object[]> resultados = actividadesRepository.listarActividades2();
        List<ActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ActividadDTO m = new ActividadDTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setNombre((String) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setPresupuesto_referencial((Double) resultado[3]);
            m.setRecursos_propios((Double) resultado[4]);
            m.setCodificado((Double) resultado[5]);
            m.setDevengado((Double) resultado[6]);
            m.setEstado((String) resultado[7]);
            m.setId_poa(((BigInteger) resultado[8]).longValue());
            acts.add(m);
        }
        return acts;
    }

    @Override
    public List<Actividades> listarActividadesPorIdPoa2(Long poaId) {
        return actividadesRepository.listarActividadesPorIdPoa2(poaId);
    }

    @Override
    public List<Actividades> listarActividadeSPORresponsable(Long id_resp) {
        return actividadesRepository.listarActividadeSPORresponsable(id_resp);
    }
    public List<UsuarioActividadesDTO> listarUsuariosActividadID(Long actividadId) {
        List<Object[]> resultados = actividadesRepository.listarUsuariosActividadID(actividadId);
        List<UsuarioActividadesDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            UsuarioActividadesDTO m = new UsuarioActividadesDTO();
            m.setId_usuario(((BigInteger) resultado[0]).longValue());
            m.setUsername((String) resultado[1]);
            m.setNombre((String) resultado[2]);
            m.setApellido((String) resultado[3]);
            m.setCargo((String) resultado[4]);
            acts.add(m);
        }
        return acts;
    }

   /* @Override
    public List<Periodo> listarPeriodosPorActividad(Long actividadId) {
        List<Object[]> resultados = actividadesRepository.listarPeriodosPorActividad(actividadId);
        List<Periodo> periodos = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Periodo periodo = new Periodo();
            periodo.setId_periodo(((BigInteger) resultado[0]).longValue());
            periodo.setPorcentaje((Double) resultado[1]);
            periodo.setReferencia((Double) resultado[2]);
            periodo.setVisible((Boolean) resultado[3]);
            periodos.add(periodo);
        }
        return periodos;
    }*/


    public List<ActividadDTO> listarActividadesPorIdResponsable(Long responsableId) {
        List<Object[]> resultados = actividadesRepository.listarActividadesPorIdResponsable(responsableId);
        List<ActividadDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ActividadDTO m = new ActividadDTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setNombre((String) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setPresupuesto_referencial((Double) resultado[3]);
            m.setRecursos_propios((Double) resultado[4]);
            m.setCodificado((Double) resultado[5]);
            m.setDevengado((Double) resultado[6]);
            m.setEstado((String) resultado[7]);
            acts.add(m);
        }
        return acts;
    }
    // Método para actualizar el campo "codificado"
    /*
     * @Transactional
     * public void actualizarCodificado(Long idActividad, double valor) {
     * Actividades actividad =
     * actividadesRepository.findById(idActividad).orElse(null);
     * if (actividad != null) {
     * double nuevoCodificado = actividad.getCodificado() + valor;
     * actividad.setCodificado(nuevoCodificado);
     * actividadesRepository.save(actividad);
     * }
     * }
     */

    // lista para conseguir los usuarios que tengana actividades
    @Override
    public List<UsuarioActividadDTO> obtenerUsuariosConActividades() {
        // Recuperar los datos crudos desde el repositorio.
        List<Object[]> resultados = actividadesRepository.obtenerUsuariosConActividades();

        // Crear una lista para almacenar los DTOs transformados.
        List<UsuarioActividadDTO> usuact = new ArrayList<>();

        // Iterar sobre cada registro recuperado.
        for (Object[] resultado : resultados) {

            // Convertir cada registro en un DTO. Tener en cuenta que el tercer valor
            // (número de actividades)
            // se recupera como un BigInteger, por lo que necesita ser convertido a un int.
            UsuarioActividadDTO usuarioActividadDTO = new UsuarioActividadDTO(
                    ((BigInteger) resultado[0]).longValue(), // id del usuario
                    (String) resultado[1], // Nombre del responsable
                    (String) resultado[2], // Cargo del responsable
                    ((BigInteger) resultado[3]).intValue() // Número de actividades (convertido de BigInteger a int)
            );

            // Agregar el DTO a la lista de resultados.
            usuact.add(usuarioActividadDTO);
        }

        // Devolver la lista de DTOs.
        return usuact;
    }

    // enpoint para conseguir la lista de actividades de un usuario
    @Override
    public List<DetalleActividadDTO> obtenerDetalleActividades(Long idUsuario) {

        // Realizar la consulta al repositorio para obtener el detalle de las
        // actividades del usuario.
        List<Object[]> resultados = actividadesRepository.obtenerDetalleActividades(idUsuario);
        // Crear una lista para almacenar los DTOs transformados.
        List<DetalleActividadDTO> detaact = new ArrayList<>();
        // Iterar sobre cada registro recuperado.
        for (Object[] resultado : resultados) {
            // Convertir cada registro en un DTO.
            DetalleActividadDTO detalleActividadDTO = new DetalleActividadDTO(
                    // variables que tiene el DTO
                    ((BigInteger) resultado[0]).longValue(), // ID de la actividad
                    (double) resultado[1], // Codificado
                    (String) resultado[2], // Descripción
                    (double) resultado[3], // Devengado
                    (String) resultado[4], // Estado
                    (String) resultado[5], // Nombre de la actividad
                    (double) resultado[6], // Presupuesto referencial
                    (double) resultado[7], // Recursos propios
                    (double) resultado[8], // presupuesto_externo
                    (String) resultado[9] // responsable
                );

            // Agregar el DTO a la lista de resultados.
            detaact.add(detalleActividadDTO);
        }
        // Devolver la lista de DTOs.
        return detaact;
    }

    // Modulo aprobacion del poa
    // Servicio que implementara las actividades del --POA--
    @Override
    public List<ActividadApPoaDTO> obtenerListaActividadesAprobPoa(Long id_poa) {
        // Realizar la consulta al repositorio para obtener el detalle de las
        // actividades del usuario.
        List<Object[]> resultados = actividadesRepository.obtenerDetalleActividadesAprob(id_poa);
        // Crear una lista para almacenar los DTOs transformados.
        List<ActividadApPoaDTO> detaact = new ArrayList<>();
        // Iterar sobre cada registro recuperado.
        for (Object[] resultado : resultados) {
            // Convertir cada registro en un DTO.
            ActividadApPoaDTO detalleActividadDTO = new ActividadApPoaDTO(
                    // variables que tiene el DTO
                    ((BigInteger) resultado[0]).longValue(), // ID de la actividad
                    (String) resultado[1], // Nombre
                    (String) resultado[2], // Descripción
                    (double) resultado[3], // Presupuesto referencial
                    (double) resultado[4],
                    (double) resultado[5], // Recursos propios
                    (String) resultado[6] // Estado
            );
            // Agregar el DTO a la lista de resultados.
            detaact.add(detalleActividadDTO);
        }
        // Devolver la lista de DTOs.
        return detaact;
    }

    @Override
    public void actualizarEstadoPorIdPoa(Long poaId, String estado) {
        actividadesRepository.actualizarEstadoPorIdPoa(poaId, estado);
    }


    @Override
    public List<ListaActividadesPresupuestosDTO> listarActividadesConTotalPresupuestos(Long poaId) {
        List<Object[]> resultados = actividadesRepository.listarActividadesConTotalPresupuestos(poaId);
        List<ListaActividadesPresupuestosDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ListaActividadesPresupuestosDTO m = new ListaActividadesPresupuestosDTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setNombre((String) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setPresupuesto_referencial((Double) resultado[3]);
            m.setRecursos_propios((Double) resultado[4]);
            m.setCodificado((Double) resultado[5]);
            m.setDevengado((Double) resultado[6]);
            m.setEstado((String) resultado[7]);
            m.setTotalpresupuestoEterno((Double) (resultado[8] != null ? resultado[8] : 0.0));
            m.setTotalreformaSuplemento((Double) (resultado[9] != null ? resultado[9] : 0.0));
            m.setTotalreformaTIncremento((Double) (resultado[10] != null ? resultado[10] : 0.0));
            m.setTotalreformaTDecremento((Double) (resultado[11] != null ? resultado[11] : 0.0));
            acts.add(m);
        }
        return acts;
    }

    @Override
    public List<ActividadesPendientesPorPoaProjection> ActividadesPendientesPorPoa(Long id_Poa) {
        return actividadesRepository.ActividadesPendientesPorPoa(id_Poa);
    }

    // listar actividades con archivos rechazados
    @Override
    public List<Actividades> listarActiEviRechazados() {
        return actividadesRepository.listarActEviRechazados();
    }
    @Override
    public void actualizarEstadoPorAprobacion(Long id_actividad, String estado){
        actividadesRepository.actualizarEstadoPorAprobacion(id_actividad,estado);
    };
    @Override
    public valorprojec valoracti(Long idact) {
        return actividadesRepository.valoracti(idact) ;
    }

    @Override
    public List<actividad_archi_projection> poaacti2(Long idres, Long idpoa) {
        return actividadesRepository.poaacti2(idres, idpoa);
    }



    public List<ActividadesporPoa_DTO>  lista_de_ActividadesPorIdPoa(Long actividadId) {
        List<Object[]> resultados = actividadesRepository. lista_de_ActividadesPorIdPoa(actividadId);
        List<ActividadesporPoa_DTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            ActividadesporPoa_DTO m = new ActividadesporPoa_DTO();
            m.setId_actividad(((BigInteger) resultado[0]).longValue());
            m.setCodificado((Double) resultado[1]);
            m.setDescripcion((String) resultado[2]);
            m.setDevengado((Double) resultado[3]);
            m.setEstado((String) resultado[4]);
            m.setNombre((String) resultado[5]);
            m.setPresupuesto_referencial((Double) resultado[6]);
            m.setRecursos_propios((Double) resultado[7]);
            m.setId_poa(((BigInteger) resultado[8]).longValue());
            acts.add(m);
        }
        return acts;
    }
    @Override
    public activ_fecha_lim_projection fechalim_act(Long idact) {
        return actividadesRepository.fechalim_act(idact) ;
    }
}