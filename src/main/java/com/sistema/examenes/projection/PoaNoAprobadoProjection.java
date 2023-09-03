package com.sistema.examenes.projection;

import java.util.Date;

public interface PoaNoAprobadoProjection {

    Long getId_poa();
    Date getFecha_inicio();
    Date getFecha_fin();
    String getLocalizacion();
    String getBarrio();
    String getComunidad();
    String getEstado();
    String getObservacion();
    String getNombre();

}
