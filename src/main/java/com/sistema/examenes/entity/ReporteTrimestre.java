package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reporte_trimestre")
public class ReporteTrimestre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte_trimestre")
    private Long id_reporte_trimestre;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "ejecucion_presupuesto")
    private double ejecucion_presupuesto;
    @Column(name = "visible")
    private boolean visible;

    //metodo para ingresar la fecha actual
    @PrePersist
    public void prePersist() {
        fecha = new Date();
    }

    //relacion con la tabla detalle_trimestre
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_trimestre")
    private DetalleTrimestre detalle_trimestre;
}
