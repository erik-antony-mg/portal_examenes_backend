package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.Categoria;
import com.sistema.examenes.entities.dto.CategoriaDto;
import com.sistema.examenes.repositories.CategoriaRepository;
import com.sistema.examenes.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;
    private ModelMapper modelMapper;
    @Override
    public Categoria createCategoria(CategoriaDto categoriaDto) {
        Categoria categoria=modelMapper.map(categoriaDto,Categoria.class);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(CategoriaDto categoriaDto, Long idCategoria) {
        Categoria categoria=categoriaRepository.findById(idCategoria)
                .orElseThrow(()-> new RuntimeException("CATEGORIA NO ENCONTRADA"));
        modelMapper.map(categoriaDto,categoria);
        categoria.setCategoriaId(idCategoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Set<Categoria> getAllCategoria() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    public Categoria getCategoria(Long idCategoria) {
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(()-> new RuntimeException("CATEGORIA NO ENCONTRADA"));
    }

    @Override
    public void deleteCategoria(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}
