package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReformaTraspaso_D;
import com.sistema.examenes.repository.ReformaTraspaso_DRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReformaTraspaso_DServiceImpl extends GenericServiceImpl<ReformaTraspaso_D, Long> implements ReformaTraspaso_DService {

    @Autowired
    ReformaTraspaso_DRepository ReformaTraspaso_DRepository;
    @Override
    public CrudRepository<ReformaTraspaso_D, Long> getDao() {
        return ReformaTraspaso_DRepository;
    }

    @Override
    public List<ReformaTraspaso_D> listarReformasTraspaso_D() {
        return ReformaTraspaso_DRepository.listarReformasTraspaso_D();
    }
}
