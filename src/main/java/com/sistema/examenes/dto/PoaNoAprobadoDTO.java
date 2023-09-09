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
    private Long id_responsable;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String estado;
    private String observacion;
    private String nombre;
    private Date fecha_aprobacion;
    private String primer_nombre;
    private String primer_apellido;
    private String username;

}
