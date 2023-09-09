package com.sistema.examenes.projection;

import java.util.Date;

public interface PoaNoAprobadoProjection {

    Long getId_poa();

    Long getId_responsable();

    Date getFecha_inicio();

    Date getFecha_fin();

    String getEstado();

    String getObservacion();

    String getNombre();

    Date getFecha_aprobacion();

    String getPrimer_nombre();

    String getPrimer_apellido();

    String getUsername();
        String getNombrePro();

}
