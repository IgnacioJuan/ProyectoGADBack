package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActividadDTO {
    private Long id_actividad;
    private String nombre;
    private String descripcion;
    private double presupuesto_referencial;
    private double codificado;
    private double devengado;
    private double recursos_propios;
    private String estado;
    private String responsable;
}
