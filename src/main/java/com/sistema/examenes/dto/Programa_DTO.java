package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Programa_DTO {
    private Long id_programa;
    private String nombre;
    private String descripcion;
}
