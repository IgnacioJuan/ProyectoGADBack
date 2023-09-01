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
public class PoaporUsuarioDTO {

    private Long id;
    private String username;
    private String localizacion;
    private String barrio;
    private String estado;
    private String nombre;

    public PoaporUsuarioDTO(Long id, String username, String localizacion, String barrio, String estado, String nombre) {
        this.id = id;
        this.username = username;
        this.localizacion = localizacion;
        this.barrio = barrio;
        this.estado = estado;
        this.nombre = nombre;
    }

   

    

    
}
