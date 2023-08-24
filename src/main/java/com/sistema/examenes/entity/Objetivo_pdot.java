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
@Table(name = "objetivopdot")
public class Objetivo_pdot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo_pdot")
    private Long id_objetivo_pdot;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @Column(name = "visible")
    private boolean visible;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_componente")
    private Componente componente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "objetivopdot")
    @JsonIgnore
    private Set<MetaPDOT> lista_metaspdots = new HashSet<>();
}
