package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "aprobacion_evidencia")
public class AprobacionEvidencia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_aprobacionevid")
    private Long id_aprobacionevid;
    @Column(name = "observacion", length = 150)
    private String observacion;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    @Column(name = "fecha_aprobacion")
    private Date fecha_aprobacion;

    //Relaciones

    //ManyToOne Archivos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evidencia")
    private Archivo_s evidencia;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
