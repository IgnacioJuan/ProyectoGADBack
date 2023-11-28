package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportIPProyecto {
   private Long id_proyecto;
   private String nombre_programa;
   private String nombre_proyecto;
   private double codificado;
   private double devengado;
   private double porc_ejecucion;
   
}
