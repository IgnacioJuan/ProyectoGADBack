package com.sistema.examenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Indicador_DTO {
    private Long id_indicador;
    private String nombre;
    private String descripcion;
    private String tipoEvaluacion;
}
