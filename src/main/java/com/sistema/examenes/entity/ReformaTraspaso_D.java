package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reforma_traspasoD", indexes = {
        @Index(name = "idx_id_actividad_reftrasd", columnList = "id_actividad")
})
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
    @org.hibernate.annotations.ForeignKey(name = "FK_REFTRASPASOD_ACTIVIDAD")
    private Actividades actividad;
}
