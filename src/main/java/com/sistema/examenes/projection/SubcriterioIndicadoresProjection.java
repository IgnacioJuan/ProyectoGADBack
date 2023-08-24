package com.sistema.examenes.projection;

public interface SubcriterioIndicadoresProjection {
    Long getId_subcriterio();
    String getNombre();
    String getDescripcion();
    boolean isVisible();
    Long getCantidadIndicadores();
}
