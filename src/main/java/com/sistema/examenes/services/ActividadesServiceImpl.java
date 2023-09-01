package com.sistema.examenes.services;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.dto.DetalleActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.entity.Archivo_s;
import com.sistema.examenes.repository.ActividadesRepository;
import com.sistema.examenes.repository.AprobacionPoaRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadesServiceImpl extends GenericServiceImpl<Actividades, Long> implements ActividadesService {

    @Autowired
    ActividadesRepository actividadesRepository;

    @Override
    public CrudRepository<Actividades, Long> getDao() {
        return actividadesRepository;
    }

    @Override
    public List<Actividades> listarActividades() {
        return actividadesRepository.listarActividades();
    }

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
            // m.setResponsable((String) resultado[8] );
            acts.add(m);
        }
        return acts;
    }

    @Override
    public List<Actividades> listarActividadeSPORresponsable(Long id_resp) {
        return actividadesRepository.listarActividadeSPORresponsable(id_resp);
    }
    public List<UsuarioActividadesDTO> listarUsuariosAsignadosAActividades() {
        List<Object[]> resultados = actividadesRepository.listarUsuariosAsignadosAActividades();
        List<UsuarioActividadesDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            UsuarioActividadesDTO m = new UsuarioActividadesDTO();
            m.setId_usuario(((BigInteger) resultado[0]).longValue());
            m.setUsername((String) resultado[1]);
            m.setNombre((String) resultado[2]);
            m.setApellido((String) resultado[3]);
            m.setCargo((String) resultado[4]);
            m.setNombreActividad((String) resultado[5]);
            acts.add(m);
        }
        return acts;
    }



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
                    (String) resultado[0], // Nombre del responsable
                    (String) resultado[1], // Cargo del responsable
                    ((BigInteger) resultado[2]).intValue() // Número de actividades (convertido de BigInteger a int)
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
                    (double) resultado[7] // Recursos propios
            );

            // Agregar el DTO a la lista de resultados.
            detaact.add(detalleActividadDTO);
        }

        // Devolver la lista de DTOs.
        return detaact;
    }
    //listar actividades con archivos rechazados
    @Override
    public List<Actividades> listarActiEviRechazados() {return actividadesRepository.listarActEviRechazados();}
}