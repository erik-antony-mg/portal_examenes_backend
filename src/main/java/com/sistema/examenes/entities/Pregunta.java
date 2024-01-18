package com.sistema.examenes.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "preguntas")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preguntaId;
    private String contenido;
    private String imagen;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String respuesta;
    @Transient
    private String respuestaDada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "examen_id_fk")
    private Examen examen;

}
