package com.sistema.examenes.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AprobacionEvidenciaDTO {
    private Long id_aprobacionevid;
    private String observacion;
    private String estado;
}
