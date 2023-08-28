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
    private double ejecutado;
    private double saldo;
}
