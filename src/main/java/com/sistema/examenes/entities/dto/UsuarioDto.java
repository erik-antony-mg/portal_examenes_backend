package com.sistema.examenes.entities.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class UsuarioDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateBirth;
    @NotBlank
    @Pattern(regexp="^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$",
            message="Número de teléfono inválido")
    private String phone;
    private boolean enable;
    private String perfil;
    private Set<String> roles;
}
