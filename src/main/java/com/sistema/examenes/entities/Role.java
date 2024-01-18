package com.sistema.examenes.entities;


import com.sistema.examenes.entities.enums.ERole;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long idRoleEntity;
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole eRole) {
    }
}
