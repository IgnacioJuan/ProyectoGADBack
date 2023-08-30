package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "periodo")
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

    //Relaciones
    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_actividad")
    private Actividades actividad;

    //OneToMany ReportePeriodo
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "periodo")
    @JsonIgnore
    private Set<ReportePeriodo> lista_reportes = new HashSet<>();
}
