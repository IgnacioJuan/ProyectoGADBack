package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportICPActividades {
   private Long id_actividad;
   private String nombre_proyecto;
   private String nombre_actividad;
   private double codificado;
   private double devengado;
   private double porc_ejecucion;

}
