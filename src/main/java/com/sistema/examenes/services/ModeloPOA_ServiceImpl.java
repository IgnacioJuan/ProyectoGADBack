package com.sistema.examenes.services;


import com.sistema.examenes.entity.ModeloPOA;
import com.sistema.examenes.repository.ModeloPOARepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloPOA_ServiceImpl  extends GenericServiceImpl<ModeloPOA, Long> implements ModeloPOA_Service {

    @Autowired
    private ModeloPOARepository repository;

    @Override
    public CrudRepository<ModeloPOA, Long> getDao() {
        return repository;
    }

    @Override
    public List<ModeloPOA> listar() {
        return repository.listarModelosPOA();
    }

    @Override
    public int inhabilitarModelos(Long id_modelo) {
        return repository.inhabilitarModelos(id_modelo);
    }
}
