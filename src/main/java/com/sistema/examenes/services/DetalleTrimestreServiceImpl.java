package com.sistema.examenes.services;

import com.sistema.examenes.entity.DetalleTrimestre;
import com.sistema.examenes.repository.DetalleTrimestreRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleTrimestreServiceImpl extends GenericServiceImpl<DetalleTrimestre, Long> implements DetalleTrimestreService{

    @Autowired
    DetalleTrimestreRepository detalleTrimestreRepository;
    @Override
    public CrudRepository<DetalleTrimestre, Long> getDao() {
        return detalleTrimestreRepository;
    }

    @Override
    public List<DetalleTrimestre> listarDetalleTrimestres() {
        return detalleTrimestreRepository.listarDetalleTrimestres();
    }
}
