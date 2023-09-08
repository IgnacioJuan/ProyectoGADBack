package com.sistema.examenes.dto;

import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SolicitudPresupuesto_DTO {
    private Long id_solicitud_presupuesto;
    private String estado;
    private Date fecha_solicitud;
    private double monto_actual;
    private double monto_total;
    private String motivo;
    private double reforma;
    private String responsable_username;
    private String superadmin_username;
    private String actividad;
    private String cargo_responsable;
    private String primer_nombre_responsable;
    private String primer_apellido_responsable;
    private String cargo_superadmin;
    private String primer_nombre_superadmin;
    private String primer_apellido_superadmin;


}
