package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportIPrograma {
   private Long id_programa;
   private String nombre;
   private double codificado;
   private double devengado;
   private double porc_ejecucion;

}
