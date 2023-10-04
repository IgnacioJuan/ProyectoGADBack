package com.sistema.examenes.dto;

import com.sistema.examenes.entity.Poa;
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
    private double recursos_propios;
    private double codificado;
    private double devengado;
    private String estado;
    private Long id_poa;
}
