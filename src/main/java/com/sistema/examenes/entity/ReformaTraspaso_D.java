package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reforma_traspasoD")
public class ReformaTraspaso_D implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reftras_d")
    private Long id_reftras_d;
    @Column(name = "valor")
    private double valor;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_actividad")
    private Actividades actividad;
}
