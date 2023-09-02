package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectByIdDto {
    private Long id_proyecto;
    private String area;
    private String cargo;
    private String nombre;
    private String codigo;
    private String nombre_componente;
    private String nombre_objetivo_ods;
    private String nombre_objetivo_pnd;
    private String nombre_objetivo_pdot;
    private String objetivo_proyecto;
    private String nombre_indicador;
    private String nombre_meta_pdot;
    private String nombre_programa;
    private String nombre_completo_persona;
    private String rango_fechas;


}
