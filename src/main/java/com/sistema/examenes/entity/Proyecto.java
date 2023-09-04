package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "proyecto")
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long id_proyecto;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "objetivo", length = 2000)
    private String objetivo;
    @Column(name = "meta", length = 2000)
    private String meta;
    private double porcentaje_alcance;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_fin;
    @Column(name = "visible")
    private boolean visible;

    @Column(name="area")
    private String area;
    @PrePersist
    public void prePersist(){
        fecha_inicio= new Date();
    }

    //Relaciones
    //ManyToOne ObjetivoODS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_ods")
    private ObjetivoODS ods;

    //ManyToOne ObjetivoPND
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_pnd")
    private ObjetivoPND pnd;

    //ManyToOne Programa
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_programa")
    private Programa programa;

    //ManyToOne ModeloPOA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_modelo_poa")
    private ModeloPOA modelopoa;

    //ManyToOne Indicador
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_indicador")
    private Indicador indicador;

    //ManyToOne CompetenciaCOOTAD
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_competencia")
    private Competencia competencia;

    //OneToMany AprobacionPOA
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "proyecto")
    @JsonIgnore
    private Set<AprobacionPoa> lista_aprobacionesPoa  = new HashSet<>();
}
