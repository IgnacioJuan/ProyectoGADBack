package com.sistema.examenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ObjetivoPdot_DTO {
    private Long id_objetivo_pdot;
    private String nombre;
    private String descripcion;
}
