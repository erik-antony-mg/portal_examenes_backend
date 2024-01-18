package com.sistema.examenes.services;

import com.sistema.examenes.entities.Examen;
import com.sistema.examenes.entities.dto.ExamenDto;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public interface ExamenService {

    Examen createExamne(ExamenDto examenDto);
    Examen actualizarExamen(ExamenDto examenDto ,Long idExamen);
    Set<Examen> obtenerExamenes();
    Examen obtenerExamen(Long idExamen);
    void eliminarExamen(Long idExamen);
    Set<Examen> getAllExamByCategoriaId(Long categoriaId);
    Set<Examen> getAllExamsByCategoriaIdEnable(boolean activo,Long categoriaId);
    Set<Examen> getAllExamensEnable(boolean activo);
}
