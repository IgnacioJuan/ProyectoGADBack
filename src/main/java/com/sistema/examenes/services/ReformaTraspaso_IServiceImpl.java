package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReformaTraspaso_I;
import com.sistema.examenes.repository.ReformaTraspaso_IRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReformaTraspaso_IServiceImpl extends GenericServiceImpl<ReformaTraspaso_I, Long> implements ReformaTraspaso_IService {

    @Autowired
    ReformaTraspaso_IRepository ReformaTraspaso_IRepository;
    @Override
    public CrudRepository<ReformaTraspaso_I, Long> getDao() {
        return ReformaTraspaso_IRepository;
    }

    @Override
    public List<ReformaTraspaso_I> listarReformasTraspaso_I() {
        return ReformaTraspaso_IRepository.listarReformasTraspaso_I();
    }
}
