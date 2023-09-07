package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetalleActividadDTO {

    private Long id_actividad;
    private double codificado;
    private String descripcion;
    private double devengado;
    private String estado;
    private String nombre_actividad;
    private double presupuesto_referencial;
    private double recursos_propios;
    private String nombre_responsable; // para poder mostrar en la ruta xd 
}
