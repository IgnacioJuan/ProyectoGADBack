package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PresupuestoEActividadDTO {
    private Long id_presupuesto_externo;
    private String nombre_institucion;
    private String observacion;
    private double valor;
    private Date fecha;
}