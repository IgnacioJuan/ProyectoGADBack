package com.sistema.examenes.projection;

public interface IndicadorComponenteProjection {

    Long getId_componente();
    String getCodigo();
    Long getId_indicador();
    String getDescripcion();
    String getNombre();
    String getTipo_evaluacion();
    boolean isVisible();

}
