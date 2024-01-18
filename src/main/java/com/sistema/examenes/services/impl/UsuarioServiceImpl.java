package com.sistema.examenes.services.impl;


import com.sistema.examenes.entities.Role;
import com.sistema.examenes.entities.Usuario;
import com.sistema.examenes.entities.dto.UsuarioDto;
import com.sistema.examenes.entities.enums.ERole;
import com.sistema.examenes.exceptions.UsuarioFountException;
import com.sistema.examenes.exceptions.UsuarioNotFoundException;
import com.sistema.examenes.repositories.RoleRepository;
import com.sistema.examenes.repositories.UsuarioRepository;
import com.sistema.examenes.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new UsuarioNotFoundException("usuario con el username "+username+" no encontrado"));
    }


    @Override
    @Transactional
    public Usuario createUsuario(UsuarioDto usuarioDto) {

        Optional<Usuario>usuarioOptional= usuarioRepository.findByUsernameAndEmailAndPhone(usuarioDto.getUsername()
                ,usuarioDto.getEmail()
                ,usuarioDto.getPhone());
        if (usuarioOptional.isPresent()){
            throw new UsuarioFountException("Ya existe un usuario con esos valores.");
        }
        Usuario usarioNew= modelMapper.map(usuarioDto,Usuario.class);

        if (usuarioDto.getRoles() == null || usuarioDto.getRoles().isEmpty()) {
            Role role = roleRepository.findByName(ERole.INVITADO)
                    .orElseGet(() -> Role.builder().name(ERole.INVITADO).build());
            usarioNew.setRoles(Collections.singleton(role));
        } else {
            Set<Role> roles = usuarioDto.getRoles().stream()
                    .map(roleName -> {
                        ERole enumRole = ERole.valueOf(roleName);
                        Role existingRole = roleRepository.findByName(enumRole)
                                .orElseGet(() -> Role.builder().name(enumRole).build());
                        return existingRole;
                    })
                    .collect(Collectors.toSet());
            usarioNew.setRoles(roles);
        }
        usarioNew.setPerfil("default.png");
        usarioNew.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        return usuarioRepository.save(usarioNew);
    }
    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
}
