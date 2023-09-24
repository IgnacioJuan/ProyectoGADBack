package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reforma_suplemento", indexes = {
        @Index(name = "idx_id_actividad_refsup", columnList = "id_actividad")
})
public class ReformaSuplemento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ref_suplemento")
    private Long id_ref_suplemento;
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
    @org.hibernate.annotations.ForeignKey(name = "FK_REFSUPLEMENTO_ACTIVIDAD")
    private Actividades actividad;
}
