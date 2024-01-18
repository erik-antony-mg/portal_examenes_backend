package com.sistema.examenes.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema.examenes.entities.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(name ="fecha_nacimiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateBirth;
    @Column(name ="edad")
    private Integer age;
    @Column(unique = true,name = "telefono")
    private String phone;
    private boolean enable;
    private String perfil;
    private String nombreCompleto;

    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Role.class,cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuarios_roles",joinColumns = @JoinColumn(name = "usuario_id_fk"),
    inverseJoinColumns = @JoinColumn(name = "role_id_fk"))
    private Set<Role> roles;

    @PrePersist
    private void generate(){
        age= Period.between(dateBirth,LocalDate.now()).getYears();
        nombreCompleto= nombre.concat(" ").concat(apellido);
        enable=true;

    }



}
