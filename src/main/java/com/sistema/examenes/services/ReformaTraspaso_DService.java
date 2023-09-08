package com.sistema.examenes.services;

import com.sistema.examenes.dto.RTDecrementoActividadDTO;
import com.sistema.examenes.entity.ReformaTraspaso_D;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ReformaTraspaso_DService extends GenericService<ReformaTraspaso_D, Long> {
    public List<ReformaTraspaso_D> listarReformasTraspaso_D();

    public List<RTDecrementoActividadDTO>listarRTDActividades(Long actividadId);
}
