package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "presupuesto_externo")
public class PresupuestoExterno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto_externo")
    private Long id_presupuesto_externo;
    @Column(name = "valor")
    private double valor;
    @Column(name = "nombre_institucion",length = 1000)
    private String nombre_institucion;
    @Column(name = "observacion",length = 2000)
    private String observacion;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_actividad")
    private Actividades actividad;
}
