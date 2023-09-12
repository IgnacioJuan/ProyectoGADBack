package com.sistema.examenes.services;


import com.sistema.examenes.dto.UsuarioActividadesDTO;
import com.sistema.examenes.entity.AsignacionesUsuarios;
import com.sistema.examenes.repository.AsignacionUsuarioRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AsignacionesUsuarios_ServiceImpl extends GenericServiceImpl<AsignacionesUsuarios, Long> implements AsignacionesUsuariosService {

    @Autowired
    private AsignacionUsuarioRepository repository;

    @Override
    public CrudRepository<AsignacionesUsuarios, Long> getDao() {
        return repository;
    }

    @Override
    public List<AsignacionesUsuarios> listar() {
        return repository.listarAsignacionesActividades();
    }

    @Override
    public List<UsuarioActividadesDTO> listarAsignacionesUsuarios(Long actividadId) {
        List<Object[]> resultados = repository.listarAsignacionesUsuarios(actividadId);
        List<UsuarioActividadesDTO> acts = new ArrayList<>();
        for (Object[] resultado : resultados) {
            UsuarioActividadesDTO m = new UsuarioActividadesDTO();
            m.setId_usuario(((BigInteger) resultado[0]).longValue());
            m.setUsername((String) resultado[1]);
            m.setNombre((String) resultado[2]);
            m.setApellido((String) resultado[3]);
            m.setCargo((String) resultado[4]);
            m.setFecha_asignacion((Date) resultado[5]);
            acts.add(m);
        }
        return acts;
    }

}
