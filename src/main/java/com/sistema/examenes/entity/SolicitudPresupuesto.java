package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "solicitud_presupuesto", indexes = {
        @Index(name = "idx_id_responsable_sol", columnList = "id_responsable"),
        @Index(name = "idx_id_actividad_sol", columnList = "id_actividad"),
        @Index(name = "idx_id_superadmin", columnList = "id_superadmin"),
        @Index(name = "idx_id_poa_sol", columnList = "id_poa")
})
public class SolicitudPresupuesto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_solicitud_presupuesto")
    private Long id_solicitud_presupuesto;
    @Column(name = "motivo", length = 2000)
    private String motivo;
    @Column(name = "monto_actual")
    private double monto_actual;
    @Column(name = "reforma")
    private double reforma;
    @Column(name = "monto_total")
    private double monto_total;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "visible")
    private boolean visible;
    @Column(name = "fecha_solicitud")
    private Date fecha_solicitud;


    //Relaciones

    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    @org.hibernate.annotations.ForeignKey(name = "FK_SOLPRES_ACTIVIDAD")
    private Actividades actividadSolicitud;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_superadmin")
    @org.hibernate.annotations.ForeignKey(name = "FK_SOLPRES_DESTI")
    private Usuario destinatario;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_responsable")
    @org.hibernate.annotations.ForeignKey(name = "FK_SOLPRES_RESPO")
    private Usuario responsable;
    //ManyToOne POA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poa")
    @org.hibernate.annotations.ForeignKey(name = "FK_SOLPRES_POA")
    private Poa poa;
}
