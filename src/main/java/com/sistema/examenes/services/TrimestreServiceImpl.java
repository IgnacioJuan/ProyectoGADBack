package com.sistema.examenes.services;

import com.sistema.examenes.entity.Trimestre;
import com.sistema.examenes.repository.TrimestreRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrimestreServiceImpl extends GenericServiceImpl<Trimestre, Long> implements TrimestreService{

    @Autowired
    TrimestreRepository trimestreRepository;
    @Override
    public CrudRepository<Trimestre, Long> getDao() {
        return trimestreRepository;
    }

    @Override
    public List<Trimestre> listarTrimestres() {
        return trimestreRepository.listarTrimestres();
    }
}
