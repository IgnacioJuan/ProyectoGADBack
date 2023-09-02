/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.examenes.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author LENOVO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoaNoAprobadoDTO {

    private Long id_poa;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String localizacion;
    private String barrio;
    private String comunidad;
    private String estado;
    private String observacion;
    private String nombre;

    

    
}