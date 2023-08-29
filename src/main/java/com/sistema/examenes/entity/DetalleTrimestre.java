package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "detalle_trimestre")
public class DetalleTrimestre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_detalle_trimestre")
    private Long id_detalle_trimestre;
    @Column(name = "porcentaje")
    private double porcentaje;
    @Column(name = "presupuesto_referencial")
    private double presupuesto_referencial;
    @Column(name = "ejecucion_presupuesto")
    private double ejecucion_presupuesto;
    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trimestre")
    private Trimestre trimestre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    private Actividades actividad;

    //relacion con la tabla reporte_trimestre
    @JsonIgnore
    @OneToMany(mappedBy = "detalle_trimestre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ReporteTrimestre> reporte_trimestres = new HashSet<>();
}
