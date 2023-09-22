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
@ToString
public class PoaporUsuarioDTO {

    private String cedula;
    private String username;
    private String estado;
    private String nombre;
    private String primer_nombre;
    private String primer_apellido;
    private String nombrepro;
    private Date fecha_inicio;
    private Date fecha_fin;

   
}
