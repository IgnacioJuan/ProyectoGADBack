package com.sistema.examenes.services;

import com.sistema.examenes.dto.*;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;
import java.util.Optional;

public interface ActividadesService extends GenericService<Actividades, Long> {

    public List<Actividades> listarActividades();
    List<Actividades> listarActividadesPorIdPoa(Long poaId);

    List<Actividades> listarActividadeSPORresponsable(Long id_resp);
    List<UsuarioActividadesDTO>listarUsuariosAsignadosAActividades();
    List<DetalleActividadDTO> obtenerDetalleActividades(Long idUsuario);

    //Modulo aprobacion del poa
    List<ActividadDTO> obtenerDetalleActividadesAprob(Long id_poa);
    void actualizarEstadoPorIdPoa(Long poaId, String estado);

    List<UsuarioActividadDTO> obtenerUsuariosConActividades();


    //listar actividades que tengan archivos rechazados
    public List<Actividades> listarActiEviRechazados();

    //void actualizarCodificado(Long idActividad, double valor);
    Optional<Actividades> findActividadById(Long id);

    List<ActividadDTO>listarActividadesPorIdResponsable(Long responsableId);

    List<ListaActividadesPresupuestosDTO>listarActividadesConTotalPresupuestos(Long poaId);

}
