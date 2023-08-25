package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReformaSuplemento;
import com.sistema.examenes.repository.ReformaSuplementoRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReformaSuplementoServiceImpl extends GenericServiceImpl<ReformaSuplemento, Long> implements ReformaSuplementoService{
    @Autowired
    ReformaSuplementoRepository ReformaSuplementoRepository;
    @Override
    public CrudRepository<ReformaSuplemento, Long> getDao() {
        return ReformaSuplementoRepository;
    }

    @Override
    public List<ReformaSuplemento> listarReformaSuplemento() {
        return ReformaSuplementoRepository.listarReformaSuplemento();
    }
}
