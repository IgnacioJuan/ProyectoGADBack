package com.sistema.examenes.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectsActivesDto {
    private Long id_proyecto;
    private String codigo;
    private String meta;
    private String nombre;

}
