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
@Table(name = "metapdot", indexes = {
        @Index(name = "idx_objetivo_pdot", columnList = "id_objetivo_pdot")
})
public class MetaPDOT implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta_pdot")
    private Long id_meta_pdot;
    @Column(name = "nombre", length = 1000)
    private String nombre;
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @Column(name = "meta_final")
    private double meta_final;
    @Column(name = "linea_base")
    private double linea_base;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //ManyToOne ObjetivoPDOT
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_objetivo_pdot")
    @org.hibernate.annotations.ForeignKey(name = "FK_METAPDOT_OBJETPDOT") // Nombre de la restricción
    private Objetivo_pdot objetivopdot;

    //OneToMany Indicador
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "metapdot")
    @JsonIgnore
    private Set<Indicador> lista_indicadores = new HashSet<>();
}
