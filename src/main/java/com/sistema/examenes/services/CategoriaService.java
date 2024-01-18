package com.sistema.examenes.services;

import com.sistema.examenes.entities.Categoria;
import com.sistema.examenes.entities.dto.CategoriaDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CategoriaService {

    Categoria createCategoria(CategoriaDto categoriaDto);
    Categoria actualizarCategoria(CategoriaDto categoriaDto ,Long idCategoria);
    Set<Categoria> getAllCategoria();
    Categoria getCategoria(Long idCategoria);
    void deleteCategoria(Long idCategoria);
}
