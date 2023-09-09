package com.sistema.examenes.projection;

import java.util.Date;

public interface AprobacionporActividadProjection {
    String getEvaluador();
    String getEstado();
    String getObservacion();
    Date getFecha_aprobacion();
}
