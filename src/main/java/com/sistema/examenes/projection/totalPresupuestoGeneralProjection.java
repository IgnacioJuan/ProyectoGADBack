package com.sistema.examenes.projection;

import java.util.Date;

public interface totalPresupuestoGeneralProjection {
    int getReferencia();
    Date getPeriodoInicio();
    Date getPeriodoFin();
    double getInversion();
    double getEjecucion();
}
