package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "trimestre")
public class Trimestre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_trimestre")
    private Long id_trimestre;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "visible")
    private boolean visible;
    @JsonIgnore
    @OneToMany(mappedBy = "trimestre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.Set<DetalleTrimestre> detalle_trimestres = new java.util.HashSet<>();
}
