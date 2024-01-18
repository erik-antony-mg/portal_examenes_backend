package com.sistema.examenes.services;

import com.sistema.examenes.entities.Examen;
import com.sistema.examenes.entities.Pregunta;
import com.sistema.examenes.entities.dto.PreguntaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface PreguntaService {

    Pregunta createPregunta(PreguntaDto preguntaDto);
    Pregunta editPregunta(PreguntaDto preguntaDto,Long idPregunta);
    Set<Pregunta> getAllPreguntas();
    Pregunta obtenerPregunta(Long preguntaId);
    Set<Pregunta> obtenerPreguntasDelExamen(Examen examen);
    Set<Pregunta> getAllPreguntasByIdExamen(Long examenId);
    void deletePregunta(Long preguntaId);
    Map<String,Object>  evaluarExamen(List<Pregunta> preguntas);
}
