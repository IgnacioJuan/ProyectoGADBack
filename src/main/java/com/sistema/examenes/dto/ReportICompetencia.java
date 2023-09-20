package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportICompetencia {
   private String nombre;
   private double codificado;
   private double devengado;
   private double porc_ejecucion;

}
