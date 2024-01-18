package com.sistema.examenes.entities.dto;

import com.sistema.examenes.entities.Examen;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PreguntaDto {

    @NotBlank
    private String contenido;
    private String imagen;
    @NotBlank
    private String opcion1;
    @NotBlank
    private String opcion2;
    @NotBlank
    private String opcion3;
    @NotBlank
    private String opcion4;
    @NotBlank
    private String respuesta;

    private Examen examen;
}
