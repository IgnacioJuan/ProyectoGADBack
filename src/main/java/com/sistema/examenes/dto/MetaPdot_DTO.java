package com.sistema.examenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MetaPdot_DTO {
    private Long id_meta_pdot;
    private String nombre;
    private String descripcion;
    private double porcentaje_meta;
}
