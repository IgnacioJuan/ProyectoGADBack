package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AprobacionPoa_DTO {
    private String observacion;
    private String estado;
    private Long id_aprobacionpoa;
    private String primer_nombre;
    private String primer_apellido;

}
