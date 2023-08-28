package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reporte_periodo")
public class ReportePeriodo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte_periodo")
    private Long id_periodo;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "valor_ejecutado")
    private double valor_ejecutado;
    @Column(name = "saldo")
    private double saldo;

    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_periodo")
    private Periodo periodo;
}
