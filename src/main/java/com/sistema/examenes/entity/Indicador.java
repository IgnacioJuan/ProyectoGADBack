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
@Table(name = "indicador", indexes = {
        @Index(name = "idx_id_meta_pdot", columnList = "id_meta_pdot")
})
public class Indicador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicador")
    private Long id_indicador;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @Column(name = "tipo_evaluacion")
    private String tipo_evaluacion;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //OneToMany Proyecto
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "indicador")
    @JsonIgnore
    private Set<Proyecto> lista_proyectos = new HashSet<>();

    //ManyToOne MetaPDOT
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_meta_pdot")
    @org.hibernate.annotations.ForeignKey(name = "FK_INDICADOR_METAPDOT")
    private MetaPDOT metapdot;
}
