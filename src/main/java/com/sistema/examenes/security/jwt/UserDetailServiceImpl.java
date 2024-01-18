package com.sistema.examenes.security.jwt;

import com.sistema.examenes.entities.Usuario;
import com.sistema.examenes.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario=usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("usuario " + username+" no existe"));

        Collection<? extends GrantedAuthority> authorities=usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))).collect(Collectors.toSet());

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities);


    }

}
