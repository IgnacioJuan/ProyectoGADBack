package com.sistema.examenes.projection;

import java.util.Date;

public interface presupuestPeriodoProjection {
    Long getId_actividad();
    Date getFecha_inicio();
    Date getFecha_fin();
    Double getInversion();
    int getReferencia();
    Double getPorcentaje();
    Double getEjecucion();
}
