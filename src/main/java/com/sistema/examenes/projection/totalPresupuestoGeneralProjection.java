package com.sistema.examenes.projection;

import java.util.Date;

public interface totalPresupuestoGeneralProjection {
    String getReferencia();
    Date getPeriodoInicio();
    Date getPeriodoFin();
    double getInversion();
    double getEjecucion();
}
