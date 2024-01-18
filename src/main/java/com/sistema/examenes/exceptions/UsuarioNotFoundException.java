package com.sistema.examenes.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException() {
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
