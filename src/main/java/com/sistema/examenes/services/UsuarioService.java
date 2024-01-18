package com.sistema.examenes.services;

import com.sistema.examenes.entities.Usuario;
import com.sistema.examenes.entities.dto.UsuarioDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {

    Usuario createUsuario(UsuarioDto usuarioDto);
    List<Usuario> listUsuarios();
    Usuario obtenerUsuario(String username);
    void eliminarUsuario(Long usuarioId);
}
