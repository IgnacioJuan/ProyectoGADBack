package com.sistema.examenes.services;

import com.sistema.examenes.entity.Notificacion;
import com.sistema.examenes.services.generic.GenericService;

import java.sql.Date;
import java.util.List;

public interface NotificacionService extends GenericService<Notificacion, Long> {
    public List<Notificacion> listar(Long us);
    public List<Notificacion> all(String roluserer);
    public void eliminar(Long id);
    public List<Notificacion> listarNotifi(String fecha);
    public List<Notificacion> listarulNoti(Long us);
    public List<Notificacion> all2(String roluserer);
    Date fechaeliminar();
}
