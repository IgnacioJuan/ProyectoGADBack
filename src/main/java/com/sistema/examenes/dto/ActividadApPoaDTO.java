package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActividadApPoaDTO {
    private Long id_actividad;
    private String nombre;
    private String descripcion;
    private double presupuesto_referencial;
    private double recursos_propios;
    private double recursos_externos;
    private String estado;
    }
