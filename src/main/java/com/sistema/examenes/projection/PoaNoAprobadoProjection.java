package com.sistema.examenes.projection;

import java.util.Date;

public interface PoaNoAprobadoProjection {
   Long getId_poa();
    double getMeta_alcanzar();
    Date getFecha_inicio();
    String getEstado();
    String getObservacion();
}
