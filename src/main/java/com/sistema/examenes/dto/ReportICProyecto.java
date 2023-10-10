package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportICProyecto {
   private Long id_proyecto;
   private String nombre_competencia;
   private String nombre_proyecto;
   private double codificado;
   private double devengado;
   private double porc_ejecucion;

}
