package com.sistema.examenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProyectoExportarexcelDTO {
    private Long id_proyecto;
    private String codigo;
    private String nombre;
    private String objetivo;
    private String meta;
    private String nombre_objetivopnd;
    private String nombre_objetivoods;
    private String nombre_programa;
    private String nombre_indicador;
    private String nombre_metapdot;
    private String nombre_objetivopdot;
    private String nombre_competencia;
}

