package com.sistema.examenes.entities.dto;

import com.sistema.examenes.entities.Categoria;
import com.sistema.examenes.entities.Pregunta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ExamenDto {

    @NotBlank
    private String descripcion;
    private boolean enable;
    @NotNull
    private Integer numPregun;
    @NotNull
    private Integer puntosMax;
    @NotBlank
    private String titulo;
    private Categoria categoria;
    Set<Pregunta> preguntas=new HashSet<>();
}
