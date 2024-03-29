PGDMP                         {            Base_Gad    14.2    14.2    C           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            D           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            E           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            F           1262    44926    Base_Gad    DATABASE     f   CREATE DATABASE "Base_Gad" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "Base_Gad";
                postgres    false            �            1259    44928    actividades    TABLE       CREATE TABLE public.actividades (
    id_actividad bigint NOT NULL,
    codificado double precision,
    descripcion character varying(2000),
    devengado double precision,
    estado character varying(200),
    nombre character varying(200),
    presupuesto_referencial double precision,
    recursos_propios double precision,
    visible boolean,
    id_responsable bigint,
    id_poa bigint,
    fecha_creacion character varying(255),
    fecha_fin character varying(255),
    fecha_inicio character varying(255)
);
    DROP TABLE public.actividades;
       public         heap    postgres    false            �            1259    44927    actividades_id_actividad_seq    SEQUENCE     �   CREATE SEQUENCE public.actividades_id_actividad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.actividades_id_actividad_seq;
       public          postgres    false    210            G           0    0    actividades_id_actividad_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.actividades_id_actividad_seq OWNED BY public.actividades.id_actividad;
          public          postgres    false    209            �            1259    44937    aprobacion_actividad    TABLE     '  CREATE TABLE public.aprobacion_actividad (
    id_aprobacionact bigint NOT NULL,
    estado character varying(50),
    fecha_aprobacion timestamp without time zone,
    observacion character varying(150),
    visible boolean,
    id_actividad bigint,
    id_poa bigint,
    id_usuario bigint
);
 (   DROP TABLE public.aprobacion_actividad;
       public         heap    postgres    false            �            1259    44936 )   aprobacion_actividad_id_aprobacionact_seq    SEQUENCE     �   CREATE SEQUENCE public.aprobacion_actividad_id_aprobacionact_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 @   DROP SEQUENCE public.aprobacion_actividad_id_aprobacionact_seq;
       public          postgres    false    212            H           0    0 )   aprobacion_actividad_id_aprobacionact_seq    SEQUENCE OWNED BY     w   ALTER SEQUENCE public.aprobacion_actividad_id_aprobacionact_seq OWNED BY public.aprobacion_actividad.id_aprobacionact;
          public          postgres    false    211            �            1259    44944    aprobacion_evidencia    TABLE       CREATE TABLE public.aprobacion_evidencia (
    id_aprobacionevid bigint NOT NULL,
    estado character varying(50),
    fecha_aprobacion timestamp without time zone,
    observacion character varying(150),
    visible boolean,
    id_evidencia bigint,
    id_usuario bigint
);
 (   DROP TABLE public.aprobacion_evidencia;
       public         heap    postgres    false            �            1259    44943 *   aprobacion_evidencia_id_aprobacionevid_seq    SEQUENCE     �   CREATE SEQUENCE public.aprobacion_evidencia_id_aprobacionevid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 A   DROP SEQUENCE public.aprobacion_evidencia_id_aprobacionevid_seq;
       public          postgres    false    214            I           0    0 *   aprobacion_evidencia_id_aprobacionevid_seq    SEQUENCE OWNED BY     y   ALTER SEQUENCE public.aprobacion_evidencia_id_aprobacionevid_seq OWNED BY public.aprobacion_evidencia.id_aprobacionevid;
          public          postgres    false    213            �            1259    44951    aprobacion_poa    TABLE     "  CREATE TABLE public.aprobacion_poa (
    id_aprobacionpoa bigint NOT NULL,
    estado character varying(200),
    fecha_aprobacion timestamp without time zone,
    observacion character varying(2000),
    visible boolean,
    id_poa bigint,
    id_proyecto bigint,
    id_usuario bigint
);
 "   DROP TABLE public.aprobacion_poa;
       public         heap    postgres    false            �            1259    44950 #   aprobacion_poa_id_aprobacionpoa_seq    SEQUENCE     �   CREATE SEQUENCE public.aprobacion_poa_id_aprobacionpoa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.aprobacion_poa_id_aprobacionpoa_seq;
       public          postgres    false    216            J           0    0 #   aprobacion_poa_id_aprobacionpoa_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.aprobacion_poa_id_aprobacionpoa_seq OWNED BY public.aprobacion_poa.id_aprobacionpoa;
          public          postgres    false    215            �            1259    44960     aprobacion_solicitud_presupuesto    TABLE     *  CREATE TABLE public.aprobacion_solicitud_presupuesto (
    id_aprobacionsolicitudpres bigint NOT NULL,
    estado character varying(50),
    fecha_aprobacion timestamp without time zone,
    observacion character varying(150),
    visible boolean,
    id_solicitud bigint,
    id_usuario bigint
);
 4   DROP TABLE public.aprobacion_solicitud_presupuesto;
       public         heap    postgres    false            �            1259    44959 ?   aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq    SEQUENCE     �   CREATE SEQUENCE public.aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 V   DROP SEQUENCE public.aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq;
       public          postgres    false    218            K           0    0 ?   aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq OWNED BY public.aprobacion_solicitud_presupuesto.id_aprobacionsolicitudpres;
          public          postgres    false    217            �            1259    44967    archivo    TABLE     E  CREATE TABLE public.archivo (
    id_archivo bigint NOT NULL,
    descripcion character varying(10000),
    enlace character varying(255),
    estado character varying(50),
    fecha timestamp without time zone,
    nombre character varying(10000),
    valor double precision,
    visible boolean,
    id_actividad bigint
);
    DROP TABLE public.archivo;
       public         heap    postgres    false            �            1259    44966    archivo_id_archivo_seq    SEQUENCE        CREATE SEQUENCE public.archivo_id_archivo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.archivo_id_archivo_seq;
       public          postgres    false    220            L           0    0    archivo_id_archivo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.archivo_id_archivo_seq OWNED BY public.archivo.id_archivo;
          public          postgres    false    219            �            1259    44976    asignaciones_usuarios    TABLE     �   CREATE TABLE public.asignaciones_usuarios (
    id_asignacion bigint NOT NULL,
    fecha_asignacion timestamp without time zone,
    visible boolean,
    id_actividad bigint,
    id_usu_asignado bigint
);
 )   DROP TABLE public.asignaciones_usuarios;
       public         heap    postgres    false            �            1259    44975 '   asignaciones_usuarios_id_asignacion_seq    SEQUENCE     �   CREATE SEQUENCE public.asignaciones_usuarios_id_asignacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.asignaciones_usuarios_id_asignacion_seq;
       public          postgres    false    222            M           0    0 '   asignaciones_usuarios_id_asignacion_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.asignaciones_usuarios_id_asignacion_seq OWNED BY public.asignaciones_usuarios.id_asignacion;
          public          postgres    false    221            �            1259    44983    competencia    TABLE     �   CREATE TABLE public.competencia (
    id_competencia bigint NOT NULL,
    descripcion character varying(1000),
    nombre character varying(200),
    visible boolean
);
    DROP TABLE public.competencia;
       public         heap    postgres    false            �            1259    44982    competencia_id_competencia_seq    SEQUENCE     �   CREATE SEQUENCE public.competencia_id_competencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.competencia_id_competencia_seq;
       public          postgres    false    224            N           0    0    competencia_id_competencia_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.competencia_id_competencia_seq OWNED BY public.competencia.id_competencia;
          public          postgres    false    223            �            1259    44992 
   componente    TABLE     �   CREATE TABLE public.componente (
    id_componente bigint NOT NULL,
    codigo character varying(100),
    descripcion character varying(1000),
    nombre character varying(200),
    visible boolean
);
    DROP TABLE public.componente;
       public         heap    postgres    false            �            1259    44991    componente_id_componente_seq    SEQUENCE     �   CREATE SEQUENCE public.componente_id_componente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.componente_id_componente_seq;
       public          postgres    false    226            O           0    0    componente_id_componente_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.componente_id_componente_seq OWNED BY public.componente.id_componente;
          public          postgres    false    225            �            1259    45001    eje    TABLE     p   CREATE TABLE public.eje (
    id_eje bigint NOT NULL,
    nombre character varying(200),
    visible boolean
);
    DROP TABLE public.eje;
       public         heap    postgres    false            �            1259    45000    eje_id_eje_seq    SEQUENCE     w   CREATE SEQUENCE public.eje_id_eje_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.eje_id_eje_seq;
       public          postgres    false    228            P           0    0    eje_id_eje_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.eje_id_eje_seq OWNED BY public.eje.id_eje;
          public          postgres    false    227            �            1259    45008 	   indicador    TABLE     �   CREATE TABLE public.indicador (
    id_indicador bigint NOT NULL,
    descripcion character varying(1000),
    nombre character varying(200),
    tipo_evaluacion character varying(255),
    visible boolean,
    id_meta_pdot bigint
);
    DROP TABLE public.indicador;
       public         heap    postgres    false            �            1259    45007    indicador_id_indicador_seq    SEQUENCE     �   CREATE SEQUENCE public.indicador_id_indicador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.indicador_id_indicador_seq;
       public          postgres    false    230            Q           0    0    indicador_id_indicador_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.indicador_id_indicador_seq OWNED BY public.indicador.id_indicador;
          public          postgres    false    229            �            1259    45017    metapdot    TABLE       CREATE TABLE public.metapdot (
    id_meta_pdot bigint NOT NULL,
    descripcion character varying(1000),
    linea_base double precision,
    meta_final double precision,
    nombre character varying(1000),
    visible boolean,
    id_objetivo_pdot bigint
);
    DROP TABLE public.metapdot;
       public         heap    postgres    false            �            1259    45016    metapdot_id_meta_pdot_seq    SEQUENCE     �   CREATE SEQUENCE public.metapdot_id_meta_pdot_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.metapdot_id_meta_pdot_seq;
       public          postgres    false    232            R           0    0    metapdot_id_meta_pdot_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.metapdot_id_meta_pdot_seq OWNED BY public.metapdot.id_meta_pdot;
          public          postgres    false    231            �            1259    45026 	   modelopoa    TABLE       CREATE TABLE public.modelopoa (
    id_modelo_poa bigint NOT NULL,
    descripcion character varying(2000),
    estado character varying(50),
    fecha_final date,
    fecha_inicial date,
    nombre character varying(100),
    visible boolean,
    id_super_admin bigint
);
    DROP TABLE public.modelopoa;
       public         heap    postgres    false            �            1259    45025    modelopoa_id_modelo_poa_seq    SEQUENCE     �   CREATE SEQUENCE public.modelopoa_id_modelo_poa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.modelopoa_id_modelo_poa_seq;
       public          postgres    false    234            S           0    0    modelopoa_id_modelo_poa_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.modelopoa_id_modelo_poa_seq OWNED BY public.modelopoa.id_modelo_poa;
          public          postgres    false    233            �            1259    45035    notificacion    TABLE     �   CREATE TABLE public.notificacion (
    id bigint NOT NULL,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    mensaje character varying(255),
    rol character varying(255),
    usuario bigint,
    visto boolean
);
     DROP TABLE public.notificacion;
       public         heap    postgres    false            �            1259    45034    notificacion_id_seq    SEQUENCE     |   CREATE SEQUENCE public.notificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.notificacion_id_seq;
       public          postgres    false    236            T           0    0    notificacion_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.notificacion_id_seq OWNED BY public.notificacion.id;
          public          postgres    false    235            �            1259    45045    objetivoods    TABLE     �   CREATE TABLE public.objetivoods (
    id_objetivo_ods bigint NOT NULL,
    descripcion character varying(1000),
    nombre character varying(200),
    visible boolean
);
    DROP TABLE public.objetivoods;
       public         heap    postgres    false            �            1259    45044    objetivoods_id_objetivo_ods_seq    SEQUENCE     �   CREATE SEQUENCE public.objetivoods_id_objetivo_ods_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.objetivoods_id_objetivo_ods_seq;
       public          postgres    false    238            U           0    0    objetivoods_id_objetivo_ods_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.objetivoods_id_objetivo_ods_seq OWNED BY public.objetivoods.id_objetivo_ods;
          public          postgres    false    237            �            1259    45054    objetivopdot    TABLE     �   CREATE TABLE public.objetivopdot (
    id_objetivo_pdot bigint NOT NULL,
    descripcion character varying(1000),
    nombre character varying(1000),
    visible boolean,
    id_componente bigint
);
     DROP TABLE public.objetivopdot;
       public         heap    postgres    false            �            1259    45053 !   objetivopdot_id_objetivo_pdot_seq    SEQUENCE     �   CREATE SEQUENCE public.objetivopdot_id_objetivo_pdot_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.objetivopdot_id_objetivo_pdot_seq;
       public          postgres    false    240            V           0    0 !   objetivopdot_id_objetivo_pdot_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.objetivopdot_id_objetivo_pdot_seq OWNED BY public.objetivopdot.id_objetivo_pdot;
          public          postgres    false    239            �            1259    45063    objetivopnd    TABLE     �   CREATE TABLE public.objetivopnd (
    id_objetivo_pnd bigint NOT NULL,
    descripcion character varying(1000),
    nombre character varying(200),
    visible boolean,
    id_eje bigint
);
    DROP TABLE public.objetivopnd;
       public         heap    postgres    false            �            1259    45062    objetivopnd_id_objetivo_pnd_seq    SEQUENCE     �   CREATE SEQUENCE public.objetivopnd_id_objetivo_pnd_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.objetivopnd_id_objetivo_pnd_seq;
       public          postgres    false    242            W           0    0    objetivopnd_id_objetivo_pnd_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.objetivopnd_id_objetivo_pnd_seq OWNED BY public.objetivopnd.id_objetivo_pnd;
          public          postgres    false    241            �            1259    45072    periodo    TABLE     �   CREATE TABLE public.periodo (
    id_periodo bigint NOT NULL,
    porcentaje double precision,
    referencia double precision,
    visible boolean,
    id_actividad bigint,
    fecha_fin character varying(255),
    fecha_inicio character varying(255)
);
    DROP TABLE public.periodo;
       public         heap    postgres    false            �            1259    45071    periodo_id_periodo_seq    SEQUENCE        CREATE SEQUENCE public.periodo_id_periodo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.periodo_id_periodo_seq;
       public          postgres    false    244            X           0    0    periodo_id_periodo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.periodo_id_periodo_seq OWNED BY public.periodo.id_periodo;
          public          postgres    false    243            �            1259    45079    persona    TABLE     �  CREATE TABLE public.persona (
    id_persona bigint NOT NULL,
    cargo character varying(255),
    cedula character varying(255),
    celular character varying(255),
    correo character varying(255),
    direccion character varying(255),
    primer_apellido character varying(255),
    primer_nombre character varying(255),
    segundo_apellido character varying(255),
    segundo_nombre character varying(255),
    visible boolean
);
    DROP TABLE public.persona;
       public         heap    postgres    false            �            1259    45078    persona_id_persona_seq    SEQUENCE        CREATE SEQUENCE public.persona_id_persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.persona_id_persona_seq;
       public          postgres    false    246            Y           0    0    persona_id_persona_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.persona_id_persona_seq OWNED BY public.persona.id_persona;
          public          postgres    false    245            �            1259    45088    poa    TABLE     Y  CREATE TABLE public.poa (
    id_poa bigint NOT NULL,
    barrio character varying(255),
    cobertura character varying(255),
    comunidad character varying(255),
    estado character varying(200),
    fecha_fin timestamp without time zone,
    fecha_inicio timestamp without time zone,
    linea_base double precision,
    localizacion character varying(255),
    meta_alcanzar double precision,
    meta_planificada double precision,
    tipo_periodo character varying(255),
    visible boolean,
    id_responsable bigint,
    id_proyecto bigint,
    fecha_creacion timestamp without time zone
);
    DROP TABLE public.poa;
       public         heap    postgres    false            �            1259    45087    poa_id_poa_seq    SEQUENCE     w   CREATE SEQUENCE public.poa_id_poa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.poa_id_poa_seq;
       public          postgres    false    248            Z           0    0    poa_id_poa_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.poa_id_poa_seq OWNED BY public.poa.id_poa;
          public          postgres    false    247            �            1259    45097    presupuesto_externo    TABLE     "  CREATE TABLE public.presupuesto_externo (
    id_presupuesto_externo bigint NOT NULL,
    fecha timestamp without time zone,
    nombre_institucion character varying(1000),
    observacion character varying(2000),
    valor double precision,
    visible boolean,
    id_actividad bigint
);
 '   DROP TABLE public.presupuesto_externo;
       public         heap    postgres    false            �            1259    45096 .   presupuesto_externo_id_presupuesto_externo_seq    SEQUENCE     �   CREATE SEQUENCE public.presupuesto_externo_id_presupuesto_externo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE public.presupuesto_externo_id_presupuesto_externo_seq;
       public          postgres    false    250            [           0    0 .   presupuesto_externo_id_presupuesto_externo_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.presupuesto_externo_id_presupuesto_externo_seq OWNED BY public.presupuesto_externo.id_presupuesto_externo;
          public          postgres    false    249            �            1259    45106    programa    TABLE     �   CREATE TABLE public.programa (
    id_programa bigint NOT NULL,
    descripcion character varying(1000),
    nombre character varying(200),
    visible boolean
);
    DROP TABLE public.programa;
       public         heap    postgres    false            �            1259    45105    programa_id_programa_seq    SEQUENCE     �   CREATE SEQUENCE public.programa_id_programa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.programa_id_programa_seq;
       public          postgres    false    252            \           0    0    programa_id_programa_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.programa_id_programa_seq OWNED BY public.programa.id_programa;
          public          postgres    false    251            �            1259    45115    proyecto    TABLE     /  CREATE TABLE public.proyecto (
    id_proyecto bigint NOT NULL,
    area character varying(255),
    codigo character varying(255),
    fecha_fin timestamp without time zone,
    fecha_inicio date,
    meta character varying(2000),
    nombre character varying(200),
    objetivo character varying(2000),
    porcentaje_alcance double precision NOT NULL,
    visible boolean,
    id_competencia bigint,
    id_indicador bigint,
    id_modelo_poa bigint,
    id_ods bigint,
    id_pnd bigint,
    id_programa bigint,
    descripcion character varying(2000)
);
    DROP TABLE public.proyecto;
       public         heap    postgres    false            �            1259    45114    proyecto_id_proyecto_seq    SEQUENCE     �   CREATE SEQUENCE public.proyecto_id_proyecto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.proyecto_id_proyecto_seq;
       public          postgres    false    254            ]           0    0    proyecto_id_proyecto_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.proyecto_id_proyecto_seq OWNED BY public.proyecto.id_proyecto;
          public          postgres    false    253                        1259    45124    reforma_suplemento    TABLE     �   CREATE TABLE public.reforma_suplemento (
    id_ref_suplemento bigint NOT NULL,
    fecha timestamp without time zone,
    valor double precision,
    visible boolean,
    id_actividad bigint
);
 &   DROP TABLE public.reforma_suplemento;
       public         heap    postgres    false            �            1259    45123 (   reforma_suplemento_id_ref_suplemento_seq    SEQUENCE     �   CREATE SEQUENCE public.reforma_suplemento_id_ref_suplemento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ?   DROP SEQUENCE public.reforma_suplemento_id_ref_suplemento_seq;
       public          postgres    false    256            ^           0    0 (   reforma_suplemento_id_ref_suplemento_seq    SEQUENCE OWNED BY     u   ALTER SEQUENCE public.reforma_suplemento_id_ref_suplemento_seq OWNED BY public.reforma_suplemento.id_ref_suplemento;
          public          postgres    false    255                       1259    45131    reforma_traspasod    TABLE     �   CREATE TABLE public.reforma_traspasod (
    id_reftras_d bigint NOT NULL,
    fecha timestamp without time zone,
    valor double precision,
    visible boolean,
    id_actividad bigint
);
 %   DROP TABLE public.reforma_traspasod;
       public         heap    postgres    false                       1259    45130 "   reforma_traspasod_id_reftras_d_seq    SEQUENCE     �   CREATE SEQUENCE public.reforma_traspasod_id_reftras_d_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.reforma_traspasod_id_reftras_d_seq;
       public          postgres    false    258            _           0    0 "   reforma_traspasod_id_reftras_d_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.reforma_traspasod_id_reftras_d_seq OWNED BY public.reforma_traspasod.id_reftras_d;
          public          postgres    false    257                       1259    45138    reforma_traspasoi    TABLE     �   CREATE TABLE public.reforma_traspasoi (
    id_reftras_i bigint NOT NULL,
    fecha timestamp without time zone,
    valor double precision,
    visible boolean,
    id_actividad bigint
);
 %   DROP TABLE public.reforma_traspasoi;
       public         heap    postgres    false                       1259    45137 "   reforma_traspasoi_id_reftras_i_seq    SEQUENCE     �   CREATE SEQUENCE public.reforma_traspasoi_id_reftras_i_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.reforma_traspasoi_id_reftras_i_seq;
       public          postgres    false    260            `           0    0 "   reforma_traspasoi_id_reftras_i_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.reforma_traspasoi_id_reftras_i_seq OWNED BY public.reforma_traspasoi.id_reftras_i;
          public          postgres    false    259                       1259    45145    reporte_actividad    TABLE     �   CREATE TABLE public.reporte_actividad (
    id_reporte_actividad bigint NOT NULL,
    codificado double precision,
    devengado double precision,
    fecha timestamp without time zone,
    visible boolean,
    id_actividad bigint
);
 %   DROP TABLE public.reporte_actividad;
       public         heap    postgres    false                       1259    45144 *   reporte_actividad_id_reporte_actividad_seq    SEQUENCE     �   CREATE SEQUENCE public.reporte_actividad_id_reporte_actividad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 A   DROP SEQUENCE public.reporte_actividad_id_reporte_actividad_seq;
       public          postgres    false    262            a           0    0 *   reporte_actividad_id_reporte_actividad_seq    SEQUENCE OWNED BY     y   ALTER SEQUENCE public.reporte_actividad_id_reporte_actividad_seq OWNED BY public.reporte_actividad.id_reporte_actividad;
          public          postgres    false    261                       1259    45151    roles    TABLE     _   CREATE TABLE public.roles (
    rolid bigint NOT NULL,
    rolnombre character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            	           1259    45157    solicitud_presupuesto    TABLE     �  CREATE TABLE public.solicitud_presupuesto (
    id_solicitud_presupuesto bigint NOT NULL,
    estado character varying(50),
    fecha_solicitud timestamp without time zone,
    monto_actual double precision,
    monto_total double precision,
    motivo character varying(2000),
    reforma double precision,
    visible boolean,
    id_actividad bigint,
    id_superadmin bigint,
    id_responsable bigint
);
 )   DROP TABLE public.solicitud_presupuesto;
       public         heap    postgres    false                       1259    45156 2   solicitud_presupuesto_id_solicitud_presupuesto_seq    SEQUENCE     �   CREATE SEQUENCE public.solicitud_presupuesto_id_solicitud_presupuesto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 I   DROP SEQUENCE public.solicitud_presupuesto_id_solicitud_presupuesto_seq;
       public          postgres    false    265            b           0    0 2   solicitud_presupuesto_id_solicitud_presupuesto_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.solicitud_presupuesto_id_solicitud_presupuesto_seq OWNED BY public.solicitud_presupuesto.id_solicitud_presupuesto;
          public          postgres    false    264                       1259    45166 
   usuariorol    TABLE     r   CREATE TABLE public.usuariorol (
    usuariorolid bigint NOT NULL,
    rol_rolid bigint,
    usuario_id bigint
);
    DROP TABLE public.usuariorol;
       public         heap    postgres    false            
           1259    45165    usuariorol_usuariorolid_seq    SEQUENCE     �   CREATE SEQUENCE public.usuariorol_usuariorolid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.usuariorol_usuariorolid_seq;
       public          postgres    false    267            c           0    0    usuariorol_usuariorolid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.usuariorol_usuariorolid_seq OWNED BY public.usuariorol.usuariorolid;
          public          postgres    false    266                       1259    45173    usuarios    TABLE     �   CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255),
    username character varying(255),
    visible boolean,
    persona_id_persona bigint,
    id_programa bigint
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false                       1259    45172    usuarios_id_seq    SEQUENCE     x   CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public          postgres    false    269            d           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    268            �           2604    44931    actividades id_actividad    DEFAULT     �   ALTER TABLE ONLY public.actividades ALTER COLUMN id_actividad SET DEFAULT nextval('public.actividades_id_actividad_seq'::regclass);
 G   ALTER TABLE public.actividades ALTER COLUMN id_actividad DROP DEFAULT;
       public          postgres    false    210    209    210            �           2604    44940 %   aprobacion_actividad id_aprobacionact    DEFAULT     �   ALTER TABLE ONLY public.aprobacion_actividad ALTER COLUMN id_aprobacionact SET DEFAULT nextval('public.aprobacion_actividad_id_aprobacionact_seq'::regclass);
 T   ALTER TABLE public.aprobacion_actividad ALTER COLUMN id_aprobacionact DROP DEFAULT;
       public          postgres    false    212    211    212            �           2604    44947 &   aprobacion_evidencia id_aprobacionevid    DEFAULT     �   ALTER TABLE ONLY public.aprobacion_evidencia ALTER COLUMN id_aprobacionevid SET DEFAULT nextval('public.aprobacion_evidencia_id_aprobacionevid_seq'::regclass);
 U   ALTER TABLE public.aprobacion_evidencia ALTER COLUMN id_aprobacionevid DROP DEFAULT;
       public          postgres    false    213    214    214            �           2604    44954    aprobacion_poa id_aprobacionpoa    DEFAULT     �   ALTER TABLE ONLY public.aprobacion_poa ALTER COLUMN id_aprobacionpoa SET DEFAULT nextval('public.aprobacion_poa_id_aprobacionpoa_seq'::regclass);
 N   ALTER TABLE public.aprobacion_poa ALTER COLUMN id_aprobacionpoa DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    44963 ;   aprobacion_solicitud_presupuesto id_aprobacionsolicitudpres    DEFAULT     �   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto ALTER COLUMN id_aprobacionsolicitudpres SET DEFAULT nextval('public.aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq'::regclass);
 j   ALTER TABLE public.aprobacion_solicitud_presupuesto ALTER COLUMN id_aprobacionsolicitudpres DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    44970    archivo id_archivo    DEFAULT     x   ALTER TABLE ONLY public.archivo ALTER COLUMN id_archivo SET DEFAULT nextval('public.archivo_id_archivo_seq'::regclass);
 A   ALTER TABLE public.archivo ALTER COLUMN id_archivo DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    44979 #   asignaciones_usuarios id_asignacion    DEFAULT     �   ALTER TABLE ONLY public.asignaciones_usuarios ALTER COLUMN id_asignacion SET DEFAULT nextval('public.asignaciones_usuarios_id_asignacion_seq'::regclass);
 R   ALTER TABLE public.asignaciones_usuarios ALTER COLUMN id_asignacion DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    44986    competencia id_competencia    DEFAULT     �   ALTER TABLE ONLY public.competencia ALTER COLUMN id_competencia SET DEFAULT nextval('public.competencia_id_competencia_seq'::regclass);
 I   ALTER TABLE public.competencia ALTER COLUMN id_competencia DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    44995    componente id_componente    DEFAULT     �   ALTER TABLE ONLY public.componente ALTER COLUMN id_componente SET DEFAULT nextval('public.componente_id_componente_seq'::regclass);
 G   ALTER TABLE public.componente ALTER COLUMN id_componente DROP DEFAULT;
       public          postgres    false    225    226    226            �           2604    45004 
   eje id_eje    DEFAULT     h   ALTER TABLE ONLY public.eje ALTER COLUMN id_eje SET DEFAULT nextval('public.eje_id_eje_seq'::regclass);
 9   ALTER TABLE public.eje ALTER COLUMN id_eje DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    45011    indicador id_indicador    DEFAULT     �   ALTER TABLE ONLY public.indicador ALTER COLUMN id_indicador SET DEFAULT nextval('public.indicador_id_indicador_seq'::regclass);
 E   ALTER TABLE public.indicador ALTER COLUMN id_indicador DROP DEFAULT;
       public          postgres    false    230    229    230            �           2604    45020    metapdot id_meta_pdot    DEFAULT     ~   ALTER TABLE ONLY public.metapdot ALTER COLUMN id_meta_pdot SET DEFAULT nextval('public.metapdot_id_meta_pdot_seq'::regclass);
 D   ALTER TABLE public.metapdot ALTER COLUMN id_meta_pdot DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    45029    modelopoa id_modelo_poa    DEFAULT     �   ALTER TABLE ONLY public.modelopoa ALTER COLUMN id_modelo_poa SET DEFAULT nextval('public.modelopoa_id_modelo_poa_seq'::regclass);
 F   ALTER TABLE public.modelopoa ALTER COLUMN id_modelo_poa DROP DEFAULT;
       public          postgres    false    233    234    234            �           2604    45038    notificacion id    DEFAULT     r   ALTER TABLE ONLY public.notificacion ALTER COLUMN id SET DEFAULT nextval('public.notificacion_id_seq'::regclass);
 >   ALTER TABLE public.notificacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    236    235    236                        2604    45048    objetivoods id_objetivo_ods    DEFAULT     �   ALTER TABLE ONLY public.objetivoods ALTER COLUMN id_objetivo_ods SET DEFAULT nextval('public.objetivoods_id_objetivo_ods_seq'::regclass);
 J   ALTER TABLE public.objetivoods ALTER COLUMN id_objetivo_ods DROP DEFAULT;
       public          postgres    false    238    237    238                       2604    45057    objetivopdot id_objetivo_pdot    DEFAULT     �   ALTER TABLE ONLY public.objetivopdot ALTER COLUMN id_objetivo_pdot SET DEFAULT nextval('public.objetivopdot_id_objetivo_pdot_seq'::regclass);
 L   ALTER TABLE public.objetivopdot ALTER COLUMN id_objetivo_pdot DROP DEFAULT;
       public          postgres    false    240    239    240                       2604    45066    objetivopnd id_objetivo_pnd    DEFAULT     �   ALTER TABLE ONLY public.objetivopnd ALTER COLUMN id_objetivo_pnd SET DEFAULT nextval('public.objetivopnd_id_objetivo_pnd_seq'::regclass);
 J   ALTER TABLE public.objetivopnd ALTER COLUMN id_objetivo_pnd DROP DEFAULT;
       public          postgres    false    242    241    242                       2604    45075    periodo id_periodo    DEFAULT     x   ALTER TABLE ONLY public.periodo ALTER COLUMN id_periodo SET DEFAULT nextval('public.periodo_id_periodo_seq'::regclass);
 A   ALTER TABLE public.periodo ALTER COLUMN id_periodo DROP DEFAULT;
       public          postgres    false    244    243    244                       2604    45082    persona id_persona    DEFAULT     x   ALTER TABLE ONLY public.persona ALTER COLUMN id_persona SET DEFAULT nextval('public.persona_id_persona_seq'::regclass);
 A   ALTER TABLE public.persona ALTER COLUMN id_persona DROP DEFAULT;
       public          postgres    false    246    245    246                       2604    45091 
   poa id_poa    DEFAULT     h   ALTER TABLE ONLY public.poa ALTER COLUMN id_poa SET DEFAULT nextval('public.poa_id_poa_seq'::regclass);
 9   ALTER TABLE public.poa ALTER COLUMN id_poa DROP DEFAULT;
       public          postgres    false    247    248    248                       2604    45100 *   presupuesto_externo id_presupuesto_externo    DEFAULT     �   ALTER TABLE ONLY public.presupuesto_externo ALTER COLUMN id_presupuesto_externo SET DEFAULT nextval('public.presupuesto_externo_id_presupuesto_externo_seq'::regclass);
 Y   ALTER TABLE public.presupuesto_externo ALTER COLUMN id_presupuesto_externo DROP DEFAULT;
       public          postgres    false    250    249    250                       2604    45109    programa id_programa    DEFAULT     |   ALTER TABLE ONLY public.programa ALTER COLUMN id_programa SET DEFAULT nextval('public.programa_id_programa_seq'::regclass);
 C   ALTER TABLE public.programa ALTER COLUMN id_programa DROP DEFAULT;
       public          postgres    false    252    251    252                       2604    45118    proyecto id_proyecto    DEFAULT     |   ALTER TABLE ONLY public.proyecto ALTER COLUMN id_proyecto SET DEFAULT nextval('public.proyecto_id_proyecto_seq'::regclass);
 C   ALTER TABLE public.proyecto ALTER COLUMN id_proyecto DROP DEFAULT;
       public          postgres    false    253    254    254            	           2604    45127 $   reforma_suplemento id_ref_suplemento    DEFAULT     �   ALTER TABLE ONLY public.reforma_suplemento ALTER COLUMN id_ref_suplemento SET DEFAULT nextval('public.reforma_suplemento_id_ref_suplemento_seq'::regclass);
 S   ALTER TABLE public.reforma_suplemento ALTER COLUMN id_ref_suplemento DROP DEFAULT;
       public          postgres    false    256    255    256            
           2604    45134    reforma_traspasod id_reftras_d    DEFAULT     �   ALTER TABLE ONLY public.reforma_traspasod ALTER COLUMN id_reftras_d SET DEFAULT nextval('public.reforma_traspasod_id_reftras_d_seq'::regclass);
 M   ALTER TABLE public.reforma_traspasod ALTER COLUMN id_reftras_d DROP DEFAULT;
       public          postgres    false    258    257    258                       2604    45141    reforma_traspasoi id_reftras_i    DEFAULT     �   ALTER TABLE ONLY public.reforma_traspasoi ALTER COLUMN id_reftras_i SET DEFAULT nextval('public.reforma_traspasoi_id_reftras_i_seq'::regclass);
 M   ALTER TABLE public.reforma_traspasoi ALTER COLUMN id_reftras_i DROP DEFAULT;
       public          postgres    false    259    260    260                       2604    45148 &   reporte_actividad id_reporte_actividad    DEFAULT     �   ALTER TABLE ONLY public.reporte_actividad ALTER COLUMN id_reporte_actividad SET DEFAULT nextval('public.reporte_actividad_id_reporte_actividad_seq'::regclass);
 U   ALTER TABLE public.reporte_actividad ALTER COLUMN id_reporte_actividad DROP DEFAULT;
       public          postgres    false    262    261    262                       2604    45160 .   solicitud_presupuesto id_solicitud_presupuesto    DEFAULT     �   ALTER TABLE ONLY public.solicitud_presupuesto ALTER COLUMN id_solicitud_presupuesto SET DEFAULT nextval('public.solicitud_presupuesto_id_solicitud_presupuesto_seq'::regclass);
 ]   ALTER TABLE public.solicitud_presupuesto ALTER COLUMN id_solicitud_presupuesto DROP DEFAULT;
       public          postgres    false    265    264    265                       2604    45169    usuariorol usuariorolid    DEFAULT     �   ALTER TABLE ONLY public.usuariorol ALTER COLUMN usuariorolid SET DEFAULT nextval('public.usuariorol_usuariorolid_seq'::regclass);
 F   ALTER TABLE public.usuariorol ALTER COLUMN usuariorolid DROP DEFAULT;
       public          postgres    false    267    266    267                       2604    45176    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    268    269    269                      0    44928    actividades 
   TABLE DATA           �   COPY public.actividades (id_actividad, codificado, descripcion, devengado, estado, nombre, presupuesto_referencial, recursos_propios, visible, id_responsable, id_poa, fecha_creacion, fecha_fin, fecha_inicio) FROM stdin;
    public          postgres    false    210   +Z                0    44937    aprobacion_actividad 
   TABLE DATA           �   COPY public.aprobacion_actividad (id_aprobacionact, estado, fecha_aprobacion, observacion, visible, id_actividad, id_poa, id_usuario) FROM stdin;
    public          postgres    false    212   �Z      	          0    44944    aprobacion_evidencia 
   TABLE DATA           �   COPY public.aprobacion_evidencia (id_aprobacionevid, estado, fecha_aprobacion, observacion, visible, id_evidencia, id_usuario) FROM stdin;
    public          postgres    false    214   �[                0    44951    aprobacion_poa 
   TABLE DATA           �   COPY public.aprobacion_poa (id_aprobacionpoa, estado, fecha_aprobacion, observacion, visible, id_poa, id_proyecto, id_usuario) FROM stdin;
    public          postgres    false    216   $\                0    44960     aprobacion_solicitud_presupuesto 
   TABLE DATA           �   COPY public.aprobacion_solicitud_presupuesto (id_aprobacionsolicitudpres, estado, fecha_aprobacion, observacion, visible, id_solicitud, id_usuario) FROM stdin;
    public          postgres    false    218   �\                0    44967    archivo 
   TABLE DATA           w   COPY public.archivo (id_archivo, descripcion, enlace, estado, fecha, nombre, valor, visible, id_actividad) FROM stdin;
    public          postgres    false    220   �\                0    44976    asignaciones_usuarios 
   TABLE DATA           x   COPY public.asignaciones_usuarios (id_asignacion, fecha_asignacion, visible, id_actividad, id_usu_asignado) FROM stdin;
    public          postgres    false    222   �]                0    44983    competencia 
   TABLE DATA           S   COPY public.competencia (id_competencia, descripcion, nombre, visible) FROM stdin;
    public          postgres    false    224   	^                0    44992 
   componente 
   TABLE DATA           Y   COPY public.componente (id_componente, codigo, descripcion, nombre, visible) FROM stdin;
    public          postgres    false    226   �^                0    45001    eje 
   TABLE DATA           6   COPY public.eje (id_eje, nombre, visible) FROM stdin;
    public          postgres    false    228   /_                0    45008 	   indicador 
   TABLE DATA           n   COPY public.indicador (id_indicador, descripcion, nombre, tipo_evaluacion, visible, id_meta_pdot) FROM stdin;
    public          postgres    false    230   �_                0    45017    metapdot 
   TABLE DATA           x   COPY public.metapdot (id_meta_pdot, descripcion, linea_base, meta_final, nombre, visible, id_objetivo_pdot) FROM stdin;
    public          postgres    false    232   �a                0    45026 	   modelopoa 
   TABLE DATA           �   COPY public.modelopoa (id_modelo_poa, descripcion, estado, fecha_final, fecha_inicial, nombre, visible, id_super_admin) FROM stdin;
    public          postgres    false    234   xj                0    45035    notificacion 
   TABLE DATA           O   COPY public.notificacion (id, fecha, mensaje, rol, usuario, visto) FROM stdin;
    public          postgres    false    236   �j      !          0    45045    objetivoods 
   TABLE DATA           T   COPY public.objetivoods (id_objetivo_ods, descripcion, nombre, visible) FROM stdin;
    public          postgres    false    238   �j      #          0    45054    objetivopdot 
   TABLE DATA           e   COPY public.objetivopdot (id_objetivo_pdot, descripcion, nombre, visible, id_componente) FROM stdin;
    public          postgres    false    240   ll      %          0    45063    objetivopnd 
   TABLE DATA           \   COPY public.objetivopnd (id_objetivo_pnd, descripcion, nombre, visible, id_eje) FROM stdin;
    public          postgres    false    242   �p      '          0    45072    periodo 
   TABLE DATA           u   COPY public.periodo (id_periodo, porcentaje, referencia, visible, id_actividad, fecha_fin, fecha_inicio) FROM stdin;
    public          postgres    false    244   jt      )          0    45079    persona 
   TABLE DATA           �   COPY public.persona (id_persona, cargo, cedula, celular, correo, direccion, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, visible) FROM stdin;
    public          postgres    false    246   7u      +          0    45088    poa 
   TABLE DATA           �   COPY public.poa (id_poa, barrio, cobertura, comunidad, estado, fecha_fin, fecha_inicio, linea_base, localizacion, meta_alcanzar, meta_planificada, tipo_periodo, visible, id_responsable, id_proyecto, fecha_creacion) FROM stdin;
    public          postgres    false    248   Kv      -          0    45097    presupuesto_externo 
   TABLE DATA           �   COPY public.presupuesto_externo (id_presupuesto_externo, fecha, nombre_institucion, observacion, valor, visible, id_actividad) FROM stdin;
    public          postgres    false    250   Mw      /          0    45106    programa 
   TABLE DATA           M   COPY public.programa (id_programa, descripcion, nombre, visible) FROM stdin;
    public          postgres    false    252   �w      1          0    45115    proyecto 
   TABLE DATA           �   COPY public.proyecto (id_proyecto, area, codigo, fecha_fin, fecha_inicio, meta, nombre, objetivo, porcentaje_alcance, visible, id_competencia, id_indicador, id_modelo_poa, id_ods, id_pnd, id_programa, descripcion) FROM stdin;
    public          postgres    false    254   �x      3          0    45124    reforma_suplemento 
   TABLE DATA           d   COPY public.reforma_suplemento (id_ref_suplemento, fecha, valor, visible, id_actividad) FROM stdin;
    public          postgres    false    256   �z      5          0    45131    reforma_traspasod 
   TABLE DATA           ^   COPY public.reforma_traspasod (id_reftras_d, fecha, valor, visible, id_actividad) FROM stdin;
    public          postgres    false    258   �z      7          0    45138    reforma_traspasoi 
   TABLE DATA           ^   COPY public.reforma_traspasoi (id_reftras_i, fecha, valor, visible, id_actividad) FROM stdin;
    public          postgres    false    260   �z      9          0    45145    reporte_actividad 
   TABLE DATA           v   COPY public.reporte_actividad (id_reporte_actividad, codificado, devengado, fecha, visible, id_actividad) FROM stdin;
    public          postgres    false    262   {      :          0    45151    roles 
   TABLE DATA           1   COPY public.roles (rolid, rolnombre) FROM stdin;
    public          postgres    false    263   ){      <          0    45157    solicitud_presupuesto 
   TABLE DATA           �   COPY public.solicitud_presupuesto (id_solicitud_presupuesto, estado, fecha_solicitud, monto_actual, monto_total, motivo, reforma, visible, id_actividad, id_superadmin, id_responsable) FROM stdin;
    public          postgres    false    265   p{      >          0    45166 
   usuariorol 
   TABLE DATA           I   COPY public.usuariorol (usuariorolid, rol_rolid, usuario_id) FROM stdin;
    public          postgres    false    267   �{      @          0    45173    usuarios 
   TABLE DATA           m   COPY public.usuarios (id, enabled, password, username, visible, persona_id_persona, id_programa) FROM stdin;
    public          postgres    false    269   |      e           0    0    actividades_id_actividad_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.actividades_id_actividad_seq', 49, true);
          public          postgres    false    209            f           0    0 )   aprobacion_actividad_id_aprobacionact_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('public.aprobacion_actividad_id_aprobacionact_seq', 43, true);
          public          postgres    false    211            g           0    0 *   aprobacion_evidencia_id_aprobacionevid_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('public.aprobacion_evidencia_id_aprobacionevid_seq', 5, true);
          public          postgres    false    213            h           0    0 #   aprobacion_poa_id_aprobacionpoa_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.aprobacion_poa_id_aprobacionpoa_seq', 21, true);
          public          postgres    false    215            i           0    0 ?   aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq    SEQUENCE SET     n   SELECT pg_catalog.setval('public.aprobacion_solicitud_presupuesto_id_aprobacionsolicitudpres_seq', 1, false);
          public          postgres    false    217            j           0    0    archivo_id_archivo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.archivo_id_archivo_seq', 3, true);
          public          postgres    false    219            k           0    0 '   asignaciones_usuarios_id_asignacion_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.asignaciones_usuarios_id_asignacion_seq', 7, true);
          public          postgres    false    221            l           0    0    competencia_id_competencia_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.competencia_id_competencia_seq', 2, true);
          public          postgres    false    223            m           0    0    componente_id_componente_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.componente_id_componente_seq', 5, true);
          public          postgres    false    225            n           0    0    eje_id_eje_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.eje_id_eje_seq', 5, true);
          public          postgres    false    227            o           0    0    indicador_id_indicador_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.indicador_id_indicador_seq', 9, true);
          public          postgres    false    229            p           0    0    metapdot_id_meta_pdot_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.metapdot_id_meta_pdot_seq', 35, true);
          public          postgres    false    231            q           0    0    modelopoa_id_modelo_poa_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.modelopoa_id_modelo_poa_seq', 1, true);
          public          postgres    false    233            r           0    0    notificacion_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.notificacion_id_seq', 1, false);
          public          postgres    false    235            s           0    0    objetivoods_id_objetivo_ods_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.objetivoods_id_objetivo_ods_seq', 18, true);
          public          postgres    false    237            t           0    0 !   objetivopdot_id_objetivo_pdot_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.objetivopdot_id_objetivo_pdot_seq', 10, true);
          public          postgres    false    239            u           0    0    objetivopnd_id_objetivo_pnd_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.objetivopnd_id_objetivo_pnd_seq', 18, true);
          public          postgres    false    241            v           0    0    periodo_id_periodo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.periodo_id_periodo_seq', 56, true);
          public          postgres    false    243            w           0    0    persona_id_persona_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.persona_id_persona_seq', 10, true);
          public          postgres    false    245            x           0    0    poa_id_poa_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.poa_id_poa_seq', 26, true);
          public          postgres    false    247            y           0    0 .   presupuesto_externo_id_presupuesto_externo_seq    SEQUENCE SET     ]   SELECT pg_catalog.setval('public.presupuesto_externo_id_presupuesto_externo_seq', 16, true);
          public          postgres    false    249            z           0    0    programa_id_programa_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.programa_id_programa_seq', 11, true);
          public          postgres    false    251            {           0    0    proyecto_id_proyecto_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.proyecto_id_proyecto_seq', 3, true);
          public          postgres    false    253            |           0    0 (   reforma_suplemento_id_ref_suplemento_seq    SEQUENCE SET     W   SELECT pg_catalog.setval('public.reforma_suplemento_id_ref_suplemento_seq', 1, false);
          public          postgres    false    255            }           0    0 "   reforma_traspasod_id_reftras_d_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.reforma_traspasod_id_reftras_d_seq', 1, false);
          public          postgres    false    257            ~           0    0 "   reforma_traspasoi_id_reftras_i_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.reforma_traspasoi_id_reftras_i_seq', 1, false);
          public          postgres    false    259                       0    0 *   reporte_actividad_id_reporte_actividad_seq    SEQUENCE SET     Y   SELECT pg_catalog.setval('public.reporte_actividad_id_reporte_actividad_seq', 1, false);
          public          postgres    false    261            �           0    0 2   solicitud_presupuesto_id_solicitud_presupuesto_seq    SEQUENCE SET     `   SELECT pg_catalog.setval('public.solicitud_presupuesto_id_solicitud_presupuesto_seq', 2, true);
          public          postgres    false    264            �           0    0    usuariorol_usuariorolid_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.usuariorol_usuariorolid_seq', 4, true);
          public          postgres    false    266            �           0    0    usuarios_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.usuarios_id_seq', 4, true);
          public          postgres    false    268                       2606    44935    actividades actividades_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT actividades_pkey PRIMARY KEY (id_actividad);
 F   ALTER TABLE ONLY public.actividades DROP CONSTRAINT actividades_pkey;
       public            postgres    false    210                       2606    44942 .   aprobacion_actividad aprobacion_actividad_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public.aprobacion_actividad
    ADD CONSTRAINT aprobacion_actividad_pkey PRIMARY KEY (id_aprobacionact);
 X   ALTER TABLE ONLY public.aprobacion_actividad DROP CONSTRAINT aprobacion_actividad_pkey;
       public            postgres    false    212                       2606    44949 .   aprobacion_evidencia aprobacion_evidencia_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY public.aprobacion_evidencia
    ADD CONSTRAINT aprobacion_evidencia_pkey PRIMARY KEY (id_aprobacionevid);
 X   ALTER TABLE ONLY public.aprobacion_evidencia DROP CONSTRAINT aprobacion_evidencia_pkey;
       public            postgres    false    214                       2606    44958 "   aprobacion_poa aprobacion_poa_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.aprobacion_poa
    ADD CONSTRAINT aprobacion_poa_pkey PRIMARY KEY (id_aprobacionpoa);
 L   ALTER TABLE ONLY public.aprobacion_poa DROP CONSTRAINT aprobacion_poa_pkey;
       public            postgres    false    216                       2606    44965 F   aprobacion_solicitud_presupuesto aprobacion_solicitud_presupuesto_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto
    ADD CONSTRAINT aprobacion_solicitud_presupuesto_pkey PRIMARY KEY (id_aprobacionsolicitudpres);
 p   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto DROP CONSTRAINT aprobacion_solicitud_presupuesto_pkey;
       public            postgres    false    218                       2606    44974    archivo archivo_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.archivo
    ADD CONSTRAINT archivo_pkey PRIMARY KEY (id_archivo);
 >   ALTER TABLE ONLY public.archivo DROP CONSTRAINT archivo_pkey;
       public            postgres    false    220                       2606    44981 0   asignaciones_usuarios asignaciones_usuarios_pkey 
   CONSTRAINT     y   ALTER TABLE ONLY public.asignaciones_usuarios
    ADD CONSTRAINT asignaciones_usuarios_pkey PRIMARY KEY (id_asignacion);
 Z   ALTER TABLE ONLY public.asignaciones_usuarios DROP CONSTRAINT asignaciones_usuarios_pkey;
       public            postgres    false    222                       2606    44990    competencia competencia_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.competencia
    ADD CONSTRAINT competencia_pkey PRIMARY KEY (id_competencia);
 F   ALTER TABLE ONLY public.competencia DROP CONSTRAINT competencia_pkey;
       public            postgres    false    224            !           2606    44999    componente componente_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.componente
    ADD CONSTRAINT componente_pkey PRIMARY KEY (id_componente);
 D   ALTER TABLE ONLY public.componente DROP CONSTRAINT componente_pkey;
       public            postgres    false    226            #           2606    45006    eje eje_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.eje
    ADD CONSTRAINT eje_pkey PRIMARY KEY (id_eje);
 6   ALTER TABLE ONLY public.eje DROP CONSTRAINT eje_pkey;
       public            postgres    false    228            %           2606    45015    indicador indicador_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.indicador
    ADD CONSTRAINT indicador_pkey PRIMARY KEY (id_indicador);
 B   ALTER TABLE ONLY public.indicador DROP CONSTRAINT indicador_pkey;
       public            postgres    false    230            '           2606    45024    metapdot metapdot_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.metapdot
    ADD CONSTRAINT metapdot_pkey PRIMARY KEY (id_meta_pdot);
 @   ALTER TABLE ONLY public.metapdot DROP CONSTRAINT metapdot_pkey;
       public            postgres    false    232            )           2606    45033    modelopoa modelopoa_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.modelopoa
    ADD CONSTRAINT modelopoa_pkey PRIMARY KEY (id_modelo_poa);
 B   ALTER TABLE ONLY public.modelopoa DROP CONSTRAINT modelopoa_pkey;
       public            postgres    false    234            +           2606    45043    notificacion notificacion_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.notificacion
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.notificacion DROP CONSTRAINT notificacion_pkey;
       public            postgres    false    236            -           2606    45052    objetivoods objetivoods_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.objetivoods
    ADD CONSTRAINT objetivoods_pkey PRIMARY KEY (id_objetivo_ods);
 F   ALTER TABLE ONLY public.objetivoods DROP CONSTRAINT objetivoods_pkey;
       public            postgres    false    238            /           2606    45061    objetivopdot objetivopdot_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.objetivopdot
    ADD CONSTRAINT objetivopdot_pkey PRIMARY KEY (id_objetivo_pdot);
 H   ALTER TABLE ONLY public.objetivopdot DROP CONSTRAINT objetivopdot_pkey;
       public            postgres    false    240            1           2606    45070    objetivopnd objetivopnd_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.objetivopnd
    ADD CONSTRAINT objetivopnd_pkey PRIMARY KEY (id_objetivo_pnd);
 F   ALTER TABLE ONLY public.objetivopnd DROP CONSTRAINT objetivopnd_pkey;
       public            postgres    false    242            3           2606    45077    periodo periodo_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.periodo
    ADD CONSTRAINT periodo_pkey PRIMARY KEY (id_periodo);
 >   ALTER TABLE ONLY public.periodo DROP CONSTRAINT periodo_pkey;
       public            postgres    false    244            5           2606    45086    persona persona_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    246            9           2606    45095    poa poa_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.poa
    ADD CONSTRAINT poa_pkey PRIMARY KEY (id_poa);
 6   ALTER TABLE ONLY public.poa DROP CONSTRAINT poa_pkey;
       public            postgres    false    248            ;           2606    45104 ,   presupuesto_externo presupuesto_externo_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.presupuesto_externo
    ADD CONSTRAINT presupuesto_externo_pkey PRIMARY KEY (id_presupuesto_externo);
 V   ALTER TABLE ONLY public.presupuesto_externo DROP CONSTRAINT presupuesto_externo_pkey;
       public            postgres    false    250            =           2606    45113    programa programa_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id_programa);
 @   ALTER TABLE ONLY public.programa DROP CONSTRAINT programa_pkey;
       public            postgres    false    252            ?           2606    45122    proyecto proyecto_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id_proyecto);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public            postgres    false    254            A           2606    45129 *   reforma_suplemento reforma_suplemento_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public.reforma_suplemento
    ADD CONSTRAINT reforma_suplemento_pkey PRIMARY KEY (id_ref_suplemento);
 T   ALTER TABLE ONLY public.reforma_suplemento DROP CONSTRAINT reforma_suplemento_pkey;
       public            postgres    false    256            C           2606    45136 (   reforma_traspasod reforma_traspasod_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.reforma_traspasod
    ADD CONSTRAINT reforma_traspasod_pkey PRIMARY KEY (id_reftras_d);
 R   ALTER TABLE ONLY public.reforma_traspasod DROP CONSTRAINT reforma_traspasod_pkey;
       public            postgres    false    258            E           2606    45143 (   reforma_traspasoi reforma_traspasoi_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.reforma_traspasoi
    ADD CONSTRAINT reforma_traspasoi_pkey PRIMARY KEY (id_reftras_i);
 R   ALTER TABLE ONLY public.reforma_traspasoi DROP CONSTRAINT reforma_traspasoi_pkey;
       public            postgres    false    260            G           2606    45150 (   reporte_actividad reporte_actividad_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.reporte_actividad
    ADD CONSTRAINT reporte_actividad_pkey PRIMARY KEY (id_reporte_actividad);
 R   ALTER TABLE ONLY public.reporte_actividad DROP CONSTRAINT reporte_actividad_pkey;
       public            postgres    false    262            I           2606    45155    roles roles_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rolid);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    263            K           2606    45164 0   solicitud_presupuesto solicitud_presupuesto_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.solicitud_presupuesto
    ADD CONSTRAINT solicitud_presupuesto_pkey PRIMARY KEY (id_solicitud_presupuesto);
 Z   ALTER TABLE ONLY public.solicitud_presupuesto DROP CONSTRAINT solicitud_presupuesto_pkey;
       public            postgres    false    265            7           2606    45182 #   persona ukcm8asjmty9arx6nqo43mfl0i6 
   CONSTRAINT     `   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT ukcm8asjmty9arx6nqo43mfl0i6 UNIQUE (cedula);
 M   ALTER TABLE ONLY public.persona DROP CONSTRAINT ukcm8asjmty9arx6nqo43mfl0i6;
       public            postgres    false    246            M           2606    45171    usuariorol usuariorol_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.usuariorol
    ADD CONSTRAINT usuariorol_pkey PRIMARY KEY (usuariorolid);
 D   ALTER TABLE ONLY public.usuariorol DROP CONSTRAINT usuariorol_pkey;
       public            postgres    false    267            O           2606    45180    usuarios usuarios_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    269            e           2606    45283    poa fk20ueus04lws53n6m6ahlvdl94    FK CONSTRAINT     �   ALTER TABLE ONLY public.poa
    ADD CONSTRAINT fk20ueus04lws53n6m6ahlvdl94 FOREIGN KEY (id_responsable) REFERENCES public.usuarios(id);
 I   ALTER TABLE ONLY public.poa DROP CONSTRAINT fk20ueus04lws53n6m6ahlvdl94;
       public          postgres    false    3407    248    269            f           2606    45385    poa fk23xqsr976l1sqoe236l7143y6    FK CONSTRAINT     �   ALTER TABLE ONLY public.poa
    ADD CONSTRAINT fk23xqsr976l1sqoe236l7143y6 FOREIGN KEY (id_proyecto) REFERENCES public.proyecto(id_proyecto);
 I   ALTER TABLE ONLY public.poa DROP CONSTRAINT fk23xqsr976l1sqoe236l7143y6;
       public          postgres    false    254    3391    248            n           2606    45323 .   reforma_suplemento fk2bl0r4ckqksk6sblo66q2ktbw    FK CONSTRAINT     �   ALTER TABLE ONLY public.reforma_suplemento
    ADD CONSTRAINT fk2bl0r4ckqksk6sblo66q2ktbw FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 X   ALTER TABLE ONLY public.reforma_suplemento DROP CONSTRAINT fk2bl0r4ckqksk6sblo66q2ktbw;
       public          postgres    false    3345    210    256            t           2606    45353 1   solicitud_presupuesto fk35qu4vcthct1a6n6uhav074uo    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud_presupuesto
    ADD CONSTRAINT fk35qu4vcthct1a6n6uhav074uo FOREIGN KEY (id_responsable) REFERENCES public.usuarios(id);
 [   ALTER TABLE ONLY public.solicitud_presupuesto DROP CONSTRAINT fk35qu4vcthct1a6n6uhav074uo;
       public          postgres    false    3407    265    269            x           2606    45373 $   usuarios fk5u1xmmt6y0o65it3af8w219ty    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk5u1xmmt6y0o65it3af8w219ty FOREIGN KEY (id_programa) REFERENCES public.programa(id_programa);
 N   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fk5u1xmmt6y0o65it3af8w219ty;
       public          postgres    false    269    3389    252            Y           2606    45223 *   aprobacion_poa fk6td6i4smhvnn9ysjvrw45wp2t    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_poa
    ADD CONSTRAINT fk6td6i4smhvnn9ysjvrw45wp2t FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id);
 T   ALTER TABLE ONLY public.aprobacion_poa DROP CONSTRAINT fk6td6i4smhvnn9ysjvrw45wp2t;
       public          postgres    false    216    269    3407            \           2606    45238 #   archivo fk6trym3e21j3hhog2hv6jobkcj    FK CONSTRAINT     �   ALTER TABLE ONLY public.archivo
    ADD CONSTRAINT fk6trym3e21j3hhog2hv6jobkcj FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 M   ALTER TABLE ONLY public.archivo DROP CONSTRAINT fk6trym3e21j3hhog2hv6jobkcj;
       public          postgres    false    210    3345    220            P           2606    45183 '   actividades fk6uyo6sqmduy0ykbjiilj66nlv    FK CONSTRAINT     �   ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fk6uyo6sqmduy0ykbjiilj66nlv FOREIGN KEY (id_responsable) REFERENCES public.usuarios(id);
 Q   ALTER TABLE ONLY public.actividades DROP CONSTRAINT fk6uyo6sqmduy0ykbjiilj66nlv;
       public          postgres    false    269    3407    210            g           2606    45288 /   presupuesto_externo fk7bj9nq6ufefe75f529oxqqna7    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_externo
    ADD CONSTRAINT fk7bj9nq6ufefe75f529oxqqna7 FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 Y   ALTER TABLE ONLY public.presupuesto_externo DROP CONSTRAINT fk7bj9nq6ufefe75f529oxqqna7;
       public          postgres    false    3345    210    250            ]           2606    45243 1   asignaciones_usuarios fk7j9wwu4158hu65h4n5ehu9snt    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignaciones_usuarios
    ADD CONSTRAINT fk7j9wwu4158hu65h4n5ehu9snt FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 [   ALTER TABLE ONLY public.asignaciones_usuarios DROP CONSTRAINT fk7j9wwu4158hu65h4n5ehu9snt;
       public          postgres    false    3345    210    222            j           2606    45303 #   proyecto fk8utugg0ef6tvtgd5lh9w3f5c    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk8utugg0ef6tvtgd5lh9w3f5c FOREIGN KEY (id_modelo_poa) REFERENCES public.modelopoa(id_modelo_poa);
 M   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk8utugg0ef6tvtgd5lh9w3f5c;
       public          postgres    false    254    3369    234            u           2606    45358 &   usuariorol fk93omfx2hj2asw60aghij55eu2    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuariorol
    ADD CONSTRAINT fk93omfx2hj2asw60aghij55eu2 FOREIGN KEY (rol_rolid) REFERENCES public.roles(rolid);
 P   ALTER TABLE ONLY public.usuariorol DROP CONSTRAINT fk93omfx2hj2asw60aghij55eu2;
       public          postgres    false    263    3401    267            d           2606    45278 #   periodo fka20yickqc93xhudep4ctaskjr    FK CONSTRAINT     �   ALTER TABLE ONLY public.periodo
    ADD CONSTRAINT fka20yickqc93xhudep4ctaskjr FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 M   ALTER TABLE ONLY public.periodo DROP CONSTRAINT fka20yickqc93xhudep4ctaskjr;
       public          postgres    false    210    244    3345            X           2606    45218 *   aprobacion_poa fka8r2hdepxjjcd2sl4ad98rlcd    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_poa
    ADD CONSTRAINT fka8r2hdepxjjcd2sl4ad98rlcd FOREIGN KEY (id_proyecto) REFERENCES public.proyecto(id_proyecto);
 T   ALTER TABLE ONLY public.aprobacion_poa DROP CONSTRAINT fka8r2hdepxjjcd2sl4ad98rlcd;
       public          postgres    false    254    216    3391            q           2606    45338 -   reporte_actividad fkbhmibeyplhk2q4fvew3dpqwen    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte_actividad
    ADD CONSTRAINT fkbhmibeyplhk2q4fvew3dpqwen FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 W   ALTER TABLE ONLY public.reporte_actividad DROP CONSTRAINT fkbhmibeyplhk2q4fvew3dpqwen;
       public          postgres    false    3345    210    262            [           2606    45233 <   aprobacion_solicitud_presupuesto fkcs0vvw0rmajjgn8ff6t9simhh    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto
    ADD CONSTRAINT fkcs0vvw0rmajjgn8ff6t9simhh FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id);
 f   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto DROP CONSTRAINT fkcs0vvw0rmajjgn8ff6t9simhh;
       public          postgres    false    3407    269    218            i           2606    45298 $   proyecto fkd8vlvm7g15thlmc3yv2de4v28    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fkd8vlvm7g15thlmc3yv2de4v28 FOREIGN KEY (id_indicador) REFERENCES public.indicador(id_indicador);
 N   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fkd8vlvm7g15thlmc3yv2de4v28;
       public          postgres    false    254    230    3365            Z           2606    45228 <   aprobacion_solicitud_presupuesto fkeeouw6yba4bnkr1vtnalxwp58    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto
    ADD CONSTRAINT fkeeouw6yba4bnkr1vtnalxwp58 FOREIGN KEY (id_solicitud) REFERENCES public.solicitud_presupuesto(id_solicitud_presupuesto);
 f   ALTER TABLE ONLY public.aprobacion_solicitud_presupuesto DROP CONSTRAINT fkeeouw6yba4bnkr1vtnalxwp58;
       public          postgres    false    3403    218    265            m           2606    45318 $   proyecto fkexgaa4s4rsmye9udf2jr2j0c5    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fkexgaa4s4rsmye9udf2jr2j0c5 FOREIGN KEY (id_programa) REFERENCES public.programa(id_programa);
 N   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fkexgaa4s4rsmye9udf2jr2j0c5;
       public          postgres    false    3389    252    254            T           2606    45198 0   aprobacion_actividad fkeyam2ijy6t16mvexbswjrn4b3    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_actividad
    ADD CONSTRAINT fkeyam2ijy6t16mvexbswjrn4b3 FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id);
 Z   ALTER TABLE ONLY public.aprobacion_actividad DROP CONSTRAINT fkeyam2ijy6t16mvexbswjrn4b3;
       public          postgres    false    269    212    3407            v           2606    45363 &   usuariorol fkfa4kgvbxpaqn2h3qaajrhx0fr    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuariorol
    ADD CONSTRAINT fkfa4kgvbxpaqn2h3qaajrhx0fr FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);
 P   ALTER TABLE ONLY public.usuariorol DROP CONSTRAINT fkfa4kgvbxpaqn2h3qaajrhx0fr;
       public          postgres    false    267    3407    269            c           2606    45273 '   objetivopnd fkgur7bt9wmaxnmf0f4bd4sb0nc    FK CONSTRAINT     �   ALTER TABLE ONLY public.objetivopnd
    ADD CONSTRAINT fkgur7bt9wmaxnmf0f4bd4sb0nc FOREIGN KEY (id_eje) REFERENCES public.eje(id_eje);
 Q   ALTER TABLE ONLY public.objetivopnd DROP CONSTRAINT fkgur7bt9wmaxnmf0f4bd4sb0nc;
       public          postgres    false    228    3363    242            `           2606    45258 $   metapdot fkhi5104mjmx974ly7fpsm92dck    FK CONSTRAINT     �   ALTER TABLE ONLY public.metapdot
    ADD CONSTRAINT fkhi5104mjmx974ly7fpsm92dck FOREIGN KEY (id_objetivo_pdot) REFERENCES public.objetivopdot(id_objetivo_pdot);
 N   ALTER TABLE ONLY public.metapdot DROP CONSTRAINT fkhi5104mjmx974ly7fpsm92dck;
       public          postgres    false    232    3375    240            h           2606    45293 $   proyecto fkikt5iwsgatgngybhawt8qlgep    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fkikt5iwsgatgngybhawt8qlgep FOREIGN KEY (id_competencia) REFERENCES public.competencia(id_competencia);
 N   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fkikt5iwsgatgngybhawt8qlgep;
       public          postgres    false    254    3359    224            p           2606    45333 -   reforma_traspasoi fkjc7k1hr4iw70bs3xv96r4xyop    FK CONSTRAINT     �   ALTER TABLE ONLY public.reforma_traspasoi
    ADD CONSTRAINT fkjc7k1hr4iw70bs3xv96r4xyop FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 W   ALTER TABLE ONLY public.reforma_traspasoi DROP CONSTRAINT fkjc7k1hr4iw70bs3xv96r4xyop;
       public          postgres    false    260    210    3345            U           2606    45203 0   aprobacion_evidencia fkji1bgs2co5pif9vhs6jse3c1p    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_evidencia
    ADD CONSTRAINT fkji1bgs2co5pif9vhs6jse3c1p FOREIGN KEY (id_evidencia) REFERENCES public.archivo(id_archivo);
 Z   ALTER TABLE ONLY public.aprobacion_evidencia DROP CONSTRAINT fkji1bgs2co5pif9vhs6jse3c1p;
       public          postgres    false    220    3355    214            k           2606    45308 $   proyecto fkk9hji1pxksgxbnwjbtcfktcdx    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fkk9hji1pxksgxbnwjbtcfktcdx FOREIGN KEY (id_ods) REFERENCES public.objetivoods(id_objetivo_ods);
 N   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fkk9hji1pxksgxbnwjbtcfktcdx;
       public          postgres    false    254    3373    238            Q           2606    45380 '   actividades fkktjklfbp7luj4288jtm4vwb6p    FK CONSTRAINT     �   ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fkktjklfbp7luj4288jtm4vwb6p FOREIGN KEY (id_poa) REFERENCES public.poa(id_poa);
 Q   ALTER TABLE ONLY public.actividades DROP CONSTRAINT fkktjklfbp7luj4288jtm4vwb6p;
       public          postgres    false    248    210    3385            o           2606    45328 -   reforma_traspasod fkkwieqwlf8k6ebtvsbkbr1o1rf    FK CONSTRAINT     �   ALTER TABLE ONLY public.reforma_traspasod
    ADD CONSTRAINT fkkwieqwlf8k6ebtvsbkbr1o1rf FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 W   ALTER TABLE ONLY public.reforma_traspasod DROP CONSTRAINT fkkwieqwlf8k6ebtvsbkbr1o1rf;
       public          postgres    false    258    3345    210            a           2606    45263 %   modelopoa fkkywme0csh3p159fqj7kmhxt89    FK CONSTRAINT     �   ALTER TABLE ONLY public.modelopoa
    ADD CONSTRAINT fkkywme0csh3p159fqj7kmhxt89 FOREIGN KEY (id_super_admin) REFERENCES public.usuarios(id);
 O   ALTER TABLE ONLY public.modelopoa DROP CONSTRAINT fkkywme0csh3p159fqj7kmhxt89;
       public          postgres    false    3407    234    269            ^           2606    45248 1   asignaciones_usuarios fkl2gpx8fsei0y6l18dbjfvh0dw    FK CONSTRAINT     �   ALTER TABLE ONLY public.asignaciones_usuarios
    ADD CONSTRAINT fkl2gpx8fsei0y6l18dbjfvh0dw FOREIGN KEY (id_usu_asignado) REFERENCES public.usuarios(id);
 [   ALTER TABLE ONLY public.asignaciones_usuarios DROP CONSTRAINT fkl2gpx8fsei0y6l18dbjfvh0dw;
       public          postgres    false    3407    269    222            W           2606    45213 *   aprobacion_poa fklw0sg6idl433070j5kemkb848    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_poa
    ADD CONSTRAINT fklw0sg6idl433070j5kemkb848 FOREIGN KEY (id_poa) REFERENCES public.poa(id_poa);
 T   ALTER TABLE ONLY public.aprobacion_poa DROP CONSTRAINT fklw0sg6idl433070j5kemkb848;
       public          postgres    false    216    248    3385            R           2606    45188 0   aprobacion_actividad fkm8p0yhrwos9r6i2qlupdbjdf1    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_actividad
    ADD CONSTRAINT fkm8p0yhrwos9r6i2qlupdbjdf1 FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 Z   ALTER TABLE ONLY public.aprobacion_actividad DROP CONSTRAINT fkm8p0yhrwos9r6i2qlupdbjdf1;
       public          postgres    false    210    212    3345            r           2606    45343 0   solicitud_presupuesto fko1pt79bkm6ysecyx5wljo612    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud_presupuesto
    ADD CONSTRAINT fko1pt79bkm6ysecyx5wljo612 FOREIGN KEY (id_actividad) REFERENCES public.actividades(id_actividad);
 Z   ALTER TABLE ONLY public.solicitud_presupuesto DROP CONSTRAINT fko1pt79bkm6ysecyx5wljo612;
       public          postgres    false    210    3345    265            s           2606    45348 1   solicitud_presupuesto fko56k91k9mnensr00yyoowh1hu    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud_presupuesto
    ADD CONSTRAINT fko56k91k9mnensr00yyoowh1hu FOREIGN KEY (id_superadmin) REFERENCES public.usuarios(id);
 [   ALTER TABLE ONLY public.solicitud_presupuesto DROP CONSTRAINT fko56k91k9mnensr00yyoowh1hu;
       public          postgres    false    265    269    3407            l           2606    45313 $   proyecto fkowem4iwnu2g0w9mi0tl3jjr8w    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fkowem4iwnu2g0w9mi0tl3jjr8w FOREIGN KEY (id_pnd) REFERENCES public.objetivopnd(id_objetivo_pnd);
 N   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fkowem4iwnu2g0w9mi0tl3jjr8w;
       public          postgres    false    242    3377    254            S           2606    45193 0   aprobacion_actividad fkp3uouwfmhf76aj92ttwur2fxo    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_actividad
    ADD CONSTRAINT fkp3uouwfmhf76aj92ttwur2fxo FOREIGN KEY (id_poa) REFERENCES public.poa(id_poa);
 Z   ALTER TABLE ONLY public.aprobacion_actividad DROP CONSTRAINT fkp3uouwfmhf76aj92ttwur2fxo;
       public          postgres    false    212    3385    248            w           2606    45368 $   usuarios fkq1l7b7bice5uysvjoo457towq    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fkq1l7b7bice5uysvjoo457towq FOREIGN KEY (persona_id_persona) REFERENCES public.persona(id_persona);
 N   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fkq1l7b7bice5uysvjoo457towq;
       public          postgres    false    246    269    3381            b           2606    45268 (   objetivopdot fkqe5f88j2ix6q2b8595oxqiq4t    FK CONSTRAINT     �   ALTER TABLE ONLY public.objetivopdot
    ADD CONSTRAINT fkqe5f88j2ix6q2b8595oxqiq4t FOREIGN KEY (id_componente) REFERENCES public.componente(id_componente);
 R   ALTER TABLE ONLY public.objetivopdot DROP CONSTRAINT fkqe5f88j2ix6q2b8595oxqiq4t;
       public          postgres    false    226    240    3361            V           2606    45208 0   aprobacion_evidencia fkr018xybxqqhr4281c19kguouq    FK CONSTRAINT     �   ALTER TABLE ONLY public.aprobacion_evidencia
    ADD CONSTRAINT fkr018xybxqqhr4281c19kguouq FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id);
 Z   ALTER TABLE ONLY public.aprobacion_evidencia DROP CONSTRAINT fkr018xybxqqhr4281c19kguouq;
       public          postgres    false    269    3407    214            _           2606    45253 %   indicador fkt3qgoofmmuetbdfwa57b8dgar    FK CONSTRAINT     �   ALTER TABLE ONLY public.indicador
    ADD CONSTRAINT fkt3qgoofmmuetbdfwa57b8dgar FOREIGN KEY (id_meta_pdot) REFERENCES public.metapdot(id_meta_pdot);
 O   ALTER TABLE ONLY public.indicador DROP CONSTRAINT fkt3qgoofmmuetbdfwa57b8dgar;
       public          postgres    false    3367    232    230               �   x�����0���_�_���q�A^TD:���HH��o��s	;����}���з0����B��t���.���a8��� Ŧޞ���ԗk{�1�8H�O�,UhH�>�T�A����N����B��d��J�H���J!M0�$�_�Y�:�%:M�I�Z��I�������`0����Xފ1c�M����&�\�B/
���         �   x��ҭ�0�a��*v[���k5B�l�&�%dp��P	�w�c�+
�n��v�c�,5���B�G���a��!�����p;�C����9=�؏0�"� �k�/)�������ڶPd`]D�"Z�BQ�خ��P�,��F�Ƒ+-�}���~��P�Y���.��I�و�ԃ\ZO�Q��P�Lx����1�ۛ      	   D   x�3�t�wrt��4202�5��54U04�24�24�333�L-.IT(H-JKM.��,�4�4����� ���         �   x�mͽ�0����*z4=�-?ݪ4�q2,EIt���z�Yؿ�{1��W��W$I%�H���,fVi��@W��� IpM[\Y��	��Zh����1��j6�;�p4VJ��H1[h��Ë�G��C�>`�?��u�NV�H�3
�-�W�	���a4L            x������ � �         �   x���1�0@�=��#�o�Q�a`p���8�4[DjJ1��K���[���]?٥��{dc|
B����)
 D�ƺ�'ű��5
�0v><L5Źuz�F�M�J��>�t��U��!m}�Fũ��������W�
��4�����e��"���5��!@�         Y   x�eͻ�0 �ڞ�b9����/	��?�)�6��!�L˃Ʃp�9��X��\ '�?P���@L;��AoA1w��Fl�[\��T��         j   x�3���tO-.�<�9O�17)35�$1����&���X���_�P�ZT���	d��*����%��� 9�%��PNq~r~QQ�B�BjEI&D	H�����4<F��� ��,�         �   x�=�1�0���9�O��=@���Z)�,!a)�Q�p�N�#e���}�]��<��
%���;�s]9Q5�����wÎ�G������p��a7��~Ci�{�GvB�-�@g�E6إ)�T.'|�`#�"Z�K-���E��$$�����VJ�  !;         g   x�3�t�JUpM��;�9739����,������C���E�)�)
�y%��E`)�THQb^qfr���y �roN�LNJ���=�K2KJ�3����b���� O�'�           x��S�n�@�uOA�KJ%��Ã�mکs�ҝz:����)��+�8VQt�$y����QY�&Y���
j=j���p��ao�p�Xq�E�<"hk`��\�w��C�ܲ��vh����_��`���/��a��$P�C5��/����v��r�K|����gyHr5�,��򊢮��Ϟ5���s'_6R��������H&\+��ѯ�Yr�����A訤p6��4'kb[p�ѕ�s�RHr���6k
�;�$!�R�h4#����H'D�j�, ��:�G;.����f)ژ�L��~CՓ�٠�f���
�O���r<�*S[J+���
;Qj>!�P+!x��K�+"M�����W�G�Ň�ͤ�R}������R�����_:�$x�?F�GI���n��&O�]�u���w�Ǻ��F�����wP�@`Ԧ�6�� ��+�?	����m�&��������P�n�u��Ѱ�&0�Sܦ7���+�3UJ�5�M         �  x��XM��F=���� 6���ӑ��ě��7F�i/�fK�A���&+��9�0���\���I���� �SY��^���jz5�ZϮ�zu����\���VwI�W���ɄJ��z�JV}��q���>�C��1�z�-��T*�T�R��e�\�*�2ԁn�]���ö���>y65Q��"�*��^-f������_/8����59�A�M��L)����lkg4�E`���L�V�t��t&2��&ꃆ����ʣ�%9����Q����u�C����ݿ�$�%ǹ�]�dMs�QG���i.�V�`k�V��=P�������Ĺ��Z}h��ȱ�m�SU8c���.\��FѴ����!;&�X�
E�"I�~�p�jW7�<���i��a֋���2@�鿧*Kbb����I�I7��J�l�����O��wu��+�T��ƨ��J���D:�*�N菸8��l:�����ҩ}��2ȣ�\��Nr����;�N�-��?Q��Nq@S*�J8E*�7&�"\@f9��b��bl�Fx�����%܍sUӠ��w|T6��7���h�c�5~�Tθ#��
<���O���~�@��u�I����;���*��p5c�*y�\SCO'��Qj �:[�"Ô�Wq����������T1�I�Aim7u5ǒa�ܡ+=��^H�2�O_}�g��G`��L:�>��t�Gp��K=�:�%�v�&�v��e�~~pTғڻ{�e��Fb�t�V�Dc���@��B?C7L�M�#�3�G���yN��eO���� ��VD�R�7gDn�vb���Nq�����N�F��@��H��H�2/��3�VƂY1�{ fɏ�����?����D#+�潻́�3����K̝ܶj���c�cH7��#�l����/��6�Jo�;�8����1D��j0)�U���2b<iln9�17>h�8��T��p,�J��鉣3��� �F�y��Йn�L7t�2@��GN�Vɪ9�>�t8������v���S)bP)��0rx�TZ�������S���:��s�X��r�����o���Ec:����=O��@F���F[������唬�S2l��`d���K "�D �-%�ȉR&��=�=�h���� %D �ckt#��4�f{A�i|�p}@X�|��{zY렀�lb�yKx���˨;G�����7��I֜k^�(�l��N�ӥ�B
:T�yS`�<�Al��~�H��5�kVj�k�=L���gX
H_,f��!H��6_ؚe�Du��wp^��X7���S~�I�=�h#�ف(�1��v�8�τUOu��<��[\����L��&�o��[uf>{)V#'��{�0��q^�5qG��Nj�,BQ����y9P���O��a��%�ve����(�d��������?Z�:k?�w��N\Z�6�'B�B�t=�� ��5Tڎt�>��q@�`���e B^/�	�NV͚��it��M����m
}O����ݦl���sQ ��j�Q�M�s�aU��I�Y�^�����bo5��lQ&0�e����@������$�F�������f;ږUED"� �1!֕�Z��낕"1@��ZJ��z9��>i�|��vv 6X�1��l�J�H�I�$�����[ΐ+�mZT	�H簞c2o2�[��O!�Gk:��8=����-tE$Ŀ���V��|�q.�@ C<��p��tߏ�E2Z��Z�~2���6>�'���
��q�m�;mu��:f�%��^��]�� ��g�M8>�b�Q�5������t�2� #����&���3�D,A���F�Ew���,��w��D���� ��r��>莗� ^#�N��|+��R}�h���<���HZ+���p-}%�ۍ�y.W'�2�����^��),,��X�ĚBm�W��(
/�^U�-���W�׻@��`�(��w?i��'�:�rv<@P���D����`P�%�#��v���ҋ!O��F����# 9V^���^��7Y7K�����XU�I�S/Z��DQ�WEܲ�n��@ͧ,�Q�����$��O�+Z?�{mf\��Џ���w�"y7��������9���l�`s<YY�-~#����ʹ]�"p̪(����$;8]����*�}��aQ�P����0N��C2��E�)��6}K��}T�����������         C   x�3���tt���4206�5�" �����wq��WpqU�q��t�tvt���S�,�4����� ���            x������ � �      !   t  x�mRAn1<{_�A�m��h-�[�=���2-�RR��G9�	�X���SO+rFC�h����1e�]�\��j������h�%%��Gt�s��&���� �:��I��78��3�[`?6�3�@6�W;�}�BY��24+�����Pw����:e�
�bDckB�~����ۂI��B��,F��â)t�w��ͽ6W�"5+ϗ�Փ3�yO�{�3��?����\�z�%5h���S���M�U-����'fD{��M1�V�Rȥ�pd0���/�]q�L	iqx#�_@�O;�8�o���'|W���6q_�"�{�_�1hB�a�֓����x�;�b��m�?�OV6�Ne�J������0�</ʦ      #     x�mV�n$7��_��=���}����	��;��a���+�@�2��'�%�nξ�.�r�YU]�ܫ�ˋO��c~�1�dj4��t�&s.}�dg��Մem�����;��㽉|�ޙ�G?�$�`Oyį�zO�h���X����9KZ�g��Of���S3�[J�N��`��f��	֌��&<dXw�l;	9�G�!&q��K8a�#�2a�s�4�c��[C3�I�q�+~��4����'�8���<���@��\\m^@�_9	G	�l*�� *Y�#���<G$��P��L���AA����d�c���1sX��Zh.��A�2;��K�DC}@>�~�64F���`��qئ�P��v@X(��-�P�V�/��!�b�$o"�� �L�^K}o��yE*�K�G���/d-\�V���n��V����q����Sб)q��	�	�=�A����] 9�Q�^����+�7�5 �@r����cB��N�	PZ�r��-;��
Lh�������x����p0���E�J�� ;{κ�=�wji���;^�$��[0�T���5�kFױ��ހ�Z7�ˎ�f�r~Ey��6����ZSSo��.< P��B��B��j�-�}�r�D- �d�Ї��}�T4�^ha���N���c9�p.����q�%	%����@�y%�G�a=��`=���p�i��.�>OM�D��y���|��+ ����:ٴ�ej��Arm��Y�+4E'L��
C[@�wR��]b�D�d�F���{��Nщc?� q���@;�����s��ZwY�_-px�lC�7��AV�ir��$���4Ȼ3E��noУ�q�����z!4�?�Y`+t�C�2Q���L{m�C^N#@=�㘩��i��17��LL!�c��ד��I��\��0���;��mE�؆��x�C6iͭ\S:��%ξ�b1�N�Y!;�/��￈�r�:ɷk���\��]����ō�f1���cgq�o��Agz���y�9��m��=���_������,U      %   �  x��U���8�����T�>��]��%W� '#p$���:��\��FΜ����Z����%�3�==�n���U�5+<p�$�l��]nL�f��B��Kr'�O��1J.���s��x=G|�Om��.<�B��v�W��F;ޠ�0�Ю�\�<�a|7}��Z�j1O������Z�'�����KC��Bֺ�{Pd.�XRs6C�Y�{�广�߻@�:�h)z��b�ݚϳ.�dF�}�ٝT����[np0>&���(hL��l��υ��,�<���n���	�w�-*G���A�7u�0=��Nl�*ݝ��ۚ? S�`V�8�EU��{��8��yg)]7E(��3p@��FU�u���[�Eb���V����9<�NE�cT��I���X���Y��~��%һ��v�֩�{���ϫ��;�����ܥ�1�|��=��sqY@fK^]Ѫ��j��d`��.������)�����Y� ��v!��X����^̱����N��h*���=���@L(|z��g�sx����E�둌��ڛ���c��I��w�N`Y�V�IнѾ~�l�VX!/�A*J���k5`��-��!֪�뮫i��Uٸ���f3��6C~�Úb��1͆O`�ǹ~ͮ��~�A}�)*D�常�l�d�LI�z�t��)�t0�%��]���*i4���^?F\J���$�e�m}4��]߅vc���C,�_�hi�!8�w��o��s�$ݝv�T�g�E�W#���. M咮�����=.��x�d�S���G�-�Q<�k3�GƟ�
Q�sj> y��&�e�/>Y������_�9�"�W���ɗ)V��j�<;�>Izh�}V�yz<�<��H�S��롨��׹�L8��|��8Wk���<���Xp�,Pk��8F�Ǭ �C��|>�c�����R��F�2�v����z��	��,6      '   �   x�U��� D�菱��i"��������?>�eR7���c%���_H�C~а-��G�VIO�7*��QP~(T4
�+�5�em���nk)��$d�(G����E�ɔ����Z�P��٥W�.ڥ�
�#{�&�]^�e��T��i�G��aB] tΩ�Nע�_�uP�l�5	�5D������K�W��a�      )     x��O�N�0=�_���$���6	��	q�Ŵf�II����I'�u?�z��O@�#˾胀�$�u)�kU	%�JAw��^h]o�=�l}-Nؿ��h�����ipch��)��[L�B�r#��e�R�Zs��Z��R
��K:߾Fk�ݢ�Ȳd�^���5m��x��p����r���`v*�5��O���Z��b�����������u�"4q�!l�C�e�!�E��<�.���Yr���	3�9�nY�/jry�      +   �   x��пn�0���� �}>���I,���M�%�H�H,}��j�?H薻o�~:�P�]�*�66m4i۶�զ��������y@�r��K��k�kI�	�ٸ��\���C՚&��}m��+`��g�����8�0N��6�i{�]�c���4u�,��"Z���IA���K�D�S�y�����ty,X='P�4��$Cu'�ʹ���u�[@�� ������I�?��
V��t,˲
+}|      -   �   x�u�1�0����@���9��E�@��r
���UP0[�J� ��%��AbF��\�H��a�����d��o��^�%����+�wy��Ía{n�̥�5�O������i�a@�"�����������n��o���vP:      /   �   x�m�;n�0Dk�<�!Dq����؅�4+�6���r@��\�s��yd�LErf��uQ�C��ɔ<���NA�RWX���{d��K��ر/�pMNƞ��Oܑ���R�C�3`�ֹ�����Y9��g�?5�cr;�l�#�y����G�cA|�D������[b��m��xmѓ&��<2own� a��$��B]Ch�9ObQ��U�ey��e�      1   �  x��R���0��`�.6D�>�)� n� i�YS{��,Ew�H�O��e�r�<\�^�wf�����>͛ƙ�iW�f+�u�wM#�֖Zs�y�4[Oɖ���ڞR����R!�F�b�@1�¡��dǀ�@�
���!b�����g�.��A	�����������{�#S�O�9xy��"di�t��3R�xnMN�0�G�葭,�(����gy��gM��� ?T�y��e�c4NL���Ƹ��3�g��S�V0_�h�a��F��}�8 �j�L%�i��1�(W���؁���sHS(��J��Z�.��$��"Vn��ui.�P���N�ܙ�/U��ܵ�vyC�ڜ��j�C	,��B酱��4o�za?3�x��n�'�C��ڃ�#����\�(F�U��mF>�2Bf���=О�10�t�Z���;��c�`��89#�ey��b�ʵ��Wj��b6���&�      3      x������ � �      5      x������ � �      7      x������ � �      9      x������ � �      :   7   x�3�tt����2�p�p�9�\�����|\�L8CC��<]]�b���� F��      <   k   x�3�p�s�t�q�4202�5��54U04�24�22�323�p����ļ��"�������҂���|��T��D��̲̔�CNC��NN#Nc�=... ^�1      >       x�3�4�4�bN#.cN �2�&\1z\\\ 4�v      @     x�M��r�0 @�u�׼T��� рv`���ZJM(��ҕ��3��`����E��"�ݪ'�X�3u�`�L�m}�9�F7��Vcx����J�N(��i����Θ<�z���]�x:�ݜ�:�\��1����2�*^I�?}��+=0r9�aVl`k🆗�����{|���r��G���j�sDz�_��F�樔��Fَ�Z%�L�9�y�I��5;���ˠ<_7�M�l��4(�,^���Z+�R�:�u�s���Ys�>tM�~��`/     