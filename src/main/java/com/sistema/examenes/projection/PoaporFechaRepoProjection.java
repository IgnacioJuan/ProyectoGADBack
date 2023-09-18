package com.sistema.examenes.projection;

import java.util.Date;

public interface PoaporFechaRepoProjection {
    String getNombre();
    Long getId_poa();
    String getBarrio();
    String getCobertura();
    String getComunidad();
    Date getFecha_inicio();
    Date getFecha_fin();
    String getEstado();
    String getLocalizacion();
    String getMeta_alcanzar();
    String getMeta_planificada();
    String getTipo_periodo();
}
