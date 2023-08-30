package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reporte_actividad")
public class ReporteActividad {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_reporte_actividad")
    private Long id_reporte_actividad;
    @Column(name = "codificado")
    private double codificado;
    @Column(name = "devengado")
    private double devengado;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "visible")
    private boolean visible;

    //metodo para ingresar la fecha actual
    @PrePersist
    public void prePersist() {
        fecha = new Date();
    }

    //Relaciones
    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    private Actividades actividad;

}
