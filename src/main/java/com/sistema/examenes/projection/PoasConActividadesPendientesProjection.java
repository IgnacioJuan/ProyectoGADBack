package com.sistema.examenes.projection;

import java.util.Date;

public interface PoasConActividadesPendientesProjection {
    Long getId_poa(); // Captura el campo id_poa de la entidad Poa
    String getEstado(); // Captura el campo estado de la entidad Poa
    String  getBarrio();
    String getComunidad();
    Date getFecha_inicio();
    Date getFecha_fin();
    int getId_responsable();
    String getNombre();
    String getCodigo();
    String getUsuario();
    Long getNro_actividades();
}
