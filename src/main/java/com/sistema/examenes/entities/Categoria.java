package com.sistema.examenes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.LinkedHashSet;

import java.util.Set;

@Entity
@Data
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    private String titulo;
    private String descripcion;


    @OneToMany(mappedBy = "categoria",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Examen> examenes=new LinkedHashSet<>();
}
