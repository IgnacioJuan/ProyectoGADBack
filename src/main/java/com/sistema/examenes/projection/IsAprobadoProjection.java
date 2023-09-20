package com.sistema.examenes.projection;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IsAprobadoProjection {
    Boolean estadoAprobado;
    Long idPoaAprobado;
}
