package com.sistema.examenes.controllers;

import com.sistema.examenes.entities.Usuario;
import com.sistema.examenes.entities.dto.UsuarioDto;
import com.sistema.examenes.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;
    /**
     * HAY UN END POINT QUE SPRING SECURITY NOS DA
     * ES EL http://localhost:8085/login esta es la razon por la que no creamos un authcontroller
     * en nuestro JwtauthenticationFiler  creamos una respuesta con este metodo successfulAuthentication que nos
     * votara el endpoint para esto el el /login necesita un username y contraseña
     * **/

    @GetMapping
    ResponseEntity<?>getAllUsuarios(){
        return ResponseEntity.ok(usuarioService.listUsuarios());
    }

    @GetMapping("/{username}")
    ResponseEntity<?>getUsuarioByUsername(@PathVariable String username){
    return ResponseEntity.ok(usuarioService.obtenerUsuario(username));
    }

    @PostMapping("/create")
    ResponseEntity<Usuario> createUsuario(@Validated @RequestBody UsuarioDto usuarioDto){
        return new ResponseEntity<>(usuarioService.createUsuario(usuarioDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{usuarioId}")
    ResponseEntity<?> deleteUsuario(@PathVariable Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
        Map<String,Object> resp=new HashMap<>();
        resp.put("mensaje","el usuario con el id "+usuarioId+" fue eliminado !!");
        return ResponseEntity.ok(resp);
    }

}
