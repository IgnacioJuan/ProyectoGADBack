package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListaActividadesPresupuestosDTO {
    private Long id_actividad;
    private String nombre;
    private String descripcion;
    private double presupuesto_referencial;
    private double codificado;
    private double devengado;
    private double recursos_propios;
    private String estado;
    private double totalpresupuestoEterno;
    private double totalreformaSuplemento;
    private double totalreformaTIncremento;
    private double totalreformaTDecremento;
}
