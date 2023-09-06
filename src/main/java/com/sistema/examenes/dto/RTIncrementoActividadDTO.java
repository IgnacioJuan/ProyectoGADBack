package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RTIncrementoActividadDTO {
    private Long id_reftras_i;
    private double valor;
    private Date fecha;
    private String nombreActividad;
    private String nombreProyecto;
}