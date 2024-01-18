package com.sistema.examenes.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.examenes.entities.Usuario;
import com.sistema.examenes.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtUtils jwtUtils;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        Usuario usuario;
        String username;
        String password;
        try{
            // de la peticion (request) uso jackson para convertirlo a una clase java
            usuario=new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
            username=usuario.getUsername();
            password=usuario.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //crea un token de autenticación con el username y contraseña específicos,
        // que se utiliza para iniciar el proceso de autenticación
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,password);

        return getAuthenticationManager()
                .authenticate(authenticationToken);
    }
    //verifica la autentificiacion
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateToken(user);
        response.addHeader("Authorization","Bearer "+token);

        Map<String, Object> resp= new HashMap<>();
        resp.put("token",token);
        resp.put("message","Autentificacion Correcta");
        resp.put("username",user.getUsername());

        //que valores llevara nuestra respuesta
        response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
