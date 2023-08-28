package com.sistema.examenes.services;

import com.sistema.examenes.entity.ReformaTraspaso_I;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ReformaTraspaso_IService extends GenericService<ReformaTraspaso_I, Long> {
    public List<ReformaTraspaso_I> listarReformasTraspaso_I();
}
