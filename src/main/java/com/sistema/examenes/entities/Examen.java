package com.sistema.examenes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "examenes")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenId;
    private String descripcion;
    private boolean enable;
    private Integer numPregun;
    private Integer puntosMax;
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id_fk")
    private Categoria categoria;

    @OneToMany(fetch =FetchType.LAZY,mappedBy = "examen",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Pregunta> preguntas=new HashSet<>();
}
