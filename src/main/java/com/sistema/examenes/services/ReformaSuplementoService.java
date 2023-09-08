package com.sistema.examenes.services;

import com.sistema.examenes.dto.ReformaSActividadDTO;
import com.sistema.examenes.entity.ReformaSuplemento;
import com.sistema.examenes.services.generic.GenericService;

import java.util.List;

public interface ReformaSuplementoService extends GenericService<ReformaSuplemento, Long> {
    public List<ReformaSuplemento> listarReformaSuplemento();

    public List<ReformaSActividadDTO>listarRSActividades(Long actividadId);
}
