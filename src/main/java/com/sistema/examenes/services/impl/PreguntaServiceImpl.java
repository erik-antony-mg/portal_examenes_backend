package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.Examen;
import com.sistema.examenes.entities.Pregunta;
import com.sistema.examenes.entities.dto.PreguntaDto;
import com.sistema.examenes.repositories.PreguntaRepository;
import com.sistema.examenes.services.PreguntaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PreguntaServiceImpl implements PreguntaService {
    private PreguntaRepository preguntaRepository;
    private ModelMapper modelMapper;
    @Override
    public Pregunta createPregunta(PreguntaDto preguntaDto) {
        Pregunta pregunta=modelMapper.map(preguntaDto,Pregunta.class);
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta editPregunta(PreguntaDto preguntaDto, Long idPregunta) {
        Pregunta pregunta=preguntaRepository.findById(idPregunta)
                .orElseThrow(()->new RuntimeException("PREGUNTA NO ENCONTRADA"));
//        Examen examen= pregunta.getExamen();
        modelMapper.map(preguntaDto,pregunta);
//        pregunta.setExamen(examen);

        pregunta.setPreguntaId(idPregunta);
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Set<Pregunta> getAllPreguntas() {
        return new HashSet<>(preguntaRepository.findAll());
    }

    @Override
    public Pregunta obtenerPregunta(Long preguntaId) {
        return preguntaRepository.findById(preguntaId)
                .orElseThrow(()->new RuntimeException("PREGUNTA NO ENCONTRADA"));
    }
    @Override
    public Set<Pregunta> obtenerPreguntasDelExamen(Examen examen) {
        return preguntaRepository.findByExamen(examen);
    }
    @Override
    public Set<Pregunta> getAllPreguntasByIdExamen(Long examenId) {
        return preguntaRepository.findAllByExamen_ExamenId(examenId);
    }
    @Override
//    public void deletePregunta(Long preguntaId) {
//        Pregunta pregunta=new Pregunta();
//        pregunta.setPreguntaId(preguntaId);
//        preguntaRepository.delete(pregunta);
//    }
    public void deletePregunta(Long preguntaId) {
        preguntaRepository.deleteById(preguntaId);
    }

    @Override
    public Map<String, Object> evaluarExamen(List<Pregunta> preguntas) {
        double puntosMaximos=0;
        Integer respuestasCorrectas=0;
        Integer intentos=0;
        for (Pregunta p:preguntas){
            Pregunta pregunta=preguntaRepository.findById(p.getPreguntaId())
                    .orElseThrow(()->new RuntimeException("PREGUNTA NO ENCONTRADA"));
            if (pregunta.getRespuesta().equals(p.getRespuestaDada())){
                respuestasCorrectas++;
                double puntos = Double.parseDouble(String.valueOf(preguntas.get(0).getExamen().getPuntosMax()))/preguntas.size();
                puntosMaximos+=puntos;
            }
            if (p.getRespuestaDada()!=null){
                intentos ++;
            }
        }

        Map<String,Object> respuestas=new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas",respuestasCorrectas);
        respuestas.put("intentos",intentos);
        return  respuestas;
    }
}
