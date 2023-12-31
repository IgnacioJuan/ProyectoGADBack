package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioActividadDTO {
    private Long id_usuario;
    private String nombre_responsable;
    private String cargo_responsable;
    private int numero_de_actividades;
}
