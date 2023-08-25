package com.sistema.examenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProyectoResumenDTO {
    private Long id_proyecto;
    private String codigo;
    private String nombre;
    private String objetivo;
    private String meta;
    private double porcentaje_alcance;
    private Date fecha_inicio;
    private Long id_objetivopnd;
    private String nombre_objetivopnd;
    private Long id_objetivoods;
    private String nombre_objetivoods;
    private Long id_modelopoa;
    private String nombre_modelopoa;
    private Long id_programa;
    private String nombre_programa;
    private Long id_indicador;
    private String nombre_indicador;
    private Long id_metapdot;
    private String nombre_metapdot;
    private Long id_objetivopdot;
    private String nombre_objetivopdot;
    private Long id_componente;
    private String nombre_componente;
    private Long id_competencia;
    private String nombre_competencia;
}
