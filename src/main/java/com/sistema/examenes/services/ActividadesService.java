package com.sistema.examenes.services;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.projection.ActividadesPendientesPorPoaProjection;
import com.sistema.examenes.projection.activ_fecha_lim_projection;
import com.sistema.examenes.projection.actividad_archi_projection;
import com.sistema.examenes.projection.valorprojec;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;
import java.util.Optional;

public interface ActividadesService extends GenericService<Actividades, Long> {

    public List<Actividades> listarActividades();

    public List<ActividadDTO> listarActividades2();
    List<ActividadDTO> listarActividadesPorIdPoa(Long poaId);

    List<Actividades> listarActividadesPorIdPoa2(Long poaId);

    List<Actividades> listarActividadeSPORresponsable(Long id_resp);
    List<DetalleActividadDTO> obtenerDetalleActividades(Long idUsuario);

    //Modulo aprobacion del poa
    List<ActividadApPoaDTO> obtenerListaActividadesAprobPoa(Long id_poa);
    void actualizarEstadoPorIdPoa(Long poaId, String estado);


    List<UsuarioActividadDTO> obtenerUsuariosConActividades();


    //listar actividades que tengan archivos rechazados
    public List<Actividades> listarActiEviRechazados();

    //void actualizarCodificado(Long idActividad, double valor);
    Optional<Actividades> findActividadById(Long id);

    List<ActividadDTO>listarActividadesPorIdResponsable(Long responsableId);

    List<ListaActividadesPresupuestosDTO>listarActividadesConTotalPresupuestos(Long poaId);
    List<ActividadesPendientesPorPoaProjection> ActividadesPendientesPorPoa(Long id_Poa);
    void actualizarEstadoPorAprobacion(Long id_actividad, String estado);

    List<UsuarioActividadesDTO>listarUsuariosActividadID(Long actividadId);

    valorprojec valoracti(Long idact);
    //List<Actividades> poaacti2(Long idres, Long idpoa);
    List<ActividadesporPoa_DTO>  lista_de_ActividadesPorIdPoa(Long poaId);

    List<actividad_archi_projection> poaacti2(Long idres, Long idpoa);
    activ_fecha_lim_projection fechalim_act(Long idact);

}
