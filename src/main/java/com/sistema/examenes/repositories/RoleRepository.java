package com.sistema.examenes.repositories;

import com.sistema.examenes.entities.Role;
import com.sistema.examenes.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
