package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "periodo", indexes = {
        @Index(name = "idx_id_actividad_peri", columnList = "id_actividad")
})
public class Periodo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Long id_periodo;
    @Column(name = "porcentaje")
    private double porcentaje;
    @Column(name = "referencia")
    private double referencia;
    @Column(name = "visible")
    private boolean visible;

    //fecha inicio y fecha fin  del periodo
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_fin;

    //Relaciones
    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_actividad")
    @org.hibernate.annotations.ForeignKey(name = "FK_PERIODO_ACTIVIDAD")
    private Actividades actividad;

}
