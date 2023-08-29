package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Componente_DTO {
    private Long id_componente;
    private String nombre;
    private String codigo;
    private String descripcion;
}
