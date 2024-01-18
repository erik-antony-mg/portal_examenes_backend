package com.sistema.examenes.repositories;

import com.sistema.examenes.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Optional <Usuario> findByUsername(String username);
    public Optional <Usuario> findByUsernameAndEmailAndPhone(String username, String email, String phone);

}

