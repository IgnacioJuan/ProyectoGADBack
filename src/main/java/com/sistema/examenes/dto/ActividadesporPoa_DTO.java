package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActividadesporPoa_DTO {
     private Long  id_actividad;
    private double   codificado;
    private String  descripcion;
    private double  devengado;
    private String    estado;
    private String  nombre;
    private double  presupuesto_referencial;
    private double  recursos_propios;
    private Long   id_poa;



}


