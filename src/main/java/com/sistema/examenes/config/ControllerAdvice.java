package com.sistema.examenes.config;

import com.sistema.examenes.exceptions.UsuarioFountException;
import com.sistema.examenes.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UsuarioFountException.class)
    public ResponseEntity<?> usuarioFountException(UsuarioFountException ex){
        Map<String,Object> error=new HashMap<>();
        error.put("codigo", HttpStatus.BAD_REQUEST);
        error.put("mensaje",ex.getMessage());

        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<?> usuarioNotFountException(UsuarioNotFoundException ex){
        Map<String,Object> error=new HashMap<>();
        error.put("codigo", HttpStatus.BAD_REQUEST);
        error.put("mensaje",ex.getMessage());

        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }



}
