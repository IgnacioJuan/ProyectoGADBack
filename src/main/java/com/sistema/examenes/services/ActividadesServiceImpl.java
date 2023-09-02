package com.sistema.examenes.services;

import com.sistema.examenes.dto.ActividadDTO;
import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.Actividades;
import com.sistema.examenes.repository.ActividadesRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadesServiceImpl extends GenericServiceImpl<Actividades, Long> implements ActividadesService{

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
            //m.setResponsable((String) resultado[8] );
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

    // Método para actualizar el campo "codificado"
    /*@Transactional
    public void actualizarCodificado(Long idActividad, double valor) {
        Actividades actividad = actividadesRepository.findById(idActividad).orElse(null);
        if (actividad != null) {
            double nuevoCodificado = actividad.getCodificado() + valor;
            actividad.setCodificado(nuevoCodificado);
            actividadesRepository.save(actividad);
        }
    }*/

}