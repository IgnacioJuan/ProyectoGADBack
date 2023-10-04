package com.sistema.examenes.projection;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface activ_fecha_lim_projection {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getFecha_fin();

}
