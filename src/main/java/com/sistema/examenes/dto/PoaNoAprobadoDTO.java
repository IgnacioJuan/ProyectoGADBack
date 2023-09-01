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
    private double meta_alcanzar;
    private Date fecha_inicio;
    private String estado;
    private String observacion; 

    public PoaNoAprobadoDTO(Long id_poa, double meta_alcanzar, Date fecha_inicio, String estado, String observacion) {
        this.id_poa = id_poa;
        this.meta_alcanzar = meta_alcanzar;
        this.fecha_inicio = fecha_inicio;
        this.estado = estado;
        this.observacion = observacion;
    }
    
  
}
