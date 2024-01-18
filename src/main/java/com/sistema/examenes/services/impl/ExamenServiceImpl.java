package com.sistema.examenes.services.impl;


import com.sistema.examenes.entities.Examen;
import com.sistema.examenes.entities.dto.ExamenDto;
import com.sistema.examenes.repositories.ExamenRepository;
import com.sistema.examenes.services.ExamenService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.LinkedHashSet;

import java.util.Set;


@AllArgsConstructor
@Service
public class ExamenServiceImpl implements ExamenService {

    private ExamenRepository examenRepository;
    private ModelMapper modelMapper;


    @Override
    public Examen createExamne(ExamenDto examenDto) {
        Examen examenNew= modelMapper.map(examenDto,Examen.class);
        return examenRepository.save(examenNew);
    }

    @Override
    public Examen actualizarExamen(ExamenDto examenDto,Long idExamen) {
        Examen examen=examenRepository.findById(idExamen)
                .orElseThrow(()-> new RuntimeException("EXAMEN NO ENCONTRADO"));
       modelMapper.map(examenDto,examen);

        examen.setExamenId(idExamen);
        return examenRepository.save(examen);
    }

    @Override
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public Examen obtenerExamen(Long idExamen) {
        return examenRepository.findById(idExamen)
                .orElseThrow(()-> new RuntimeException("EXAMEN NO ENCONTRADO"));
    }

    @Override
    public void eliminarExamen(Long idExamen) {
        examenRepository.deleteById(idExamen);
    }

    @Override
    public Set<Examen> getAllExamByCategoriaId(Long categoriaId) {
        return examenRepository.findAllByCategoriaCategoriaId(categoriaId);
    }

    @Override
    public Set<Examen> getAllExamsByCategoriaIdEnable(boolean activo, Long categoriaId) {

        return examenRepository.findAllByEnableAndCategoria_CategoriaId(activo,categoriaId);
    }

    @Override
    public Set<Examen> getAllExamensEnable(boolean activo) {
        return examenRepository.findAllByEnable(activo);
    }
}
