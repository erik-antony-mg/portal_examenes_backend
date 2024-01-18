package com.sistema.examenes.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.examenes.entities.Examen;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


import java.util.Set;

@Getter
public class CategoriaDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    private Set<Examen> examenes;
}
