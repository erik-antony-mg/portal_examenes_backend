package com.sistema.examenes.controllers;


import com.sistema.examenes.entities.Pregunta;
import com.sistema.examenes.entities.dto.PreguntaDto;
import com.sistema.examenes.services.PreguntaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/pregunta")
@AllArgsConstructor
@RestController
public class PreguntaController {
    private PreguntaService preguntaService;

    @GetMapping
    ResponseEntity<?> getAllPreguntas(){
        return ResponseEntity.ok(preguntaService.getAllPreguntas());
    }
    @GetMapping("{preguntaId}")
    ResponseEntity<?> getPreguntaById(@PathVariable Long preguntaId){
        return ResponseEntity.ok(preguntaService.obtenerPregunta(preguntaId));
    }
    @GetMapping("/examen/{examenId}")
    ResponseEntity<?> getPreguntasDelExamenById(@PathVariable Long examenId){
        return ResponseEntity.ok(preguntaService.getAllPreguntasByIdExamen(examenId));
    }
    @PostMapping("/create")
    ResponseEntity<?> createPregunta(@Validated @RequestBody PreguntaDto preguntaDto){
        return new ResponseEntity<>(preguntaService.createPregunta(preguntaDto), HttpStatus.CREATED);
    }
    @PutMapping("/edit/{preguntaid}")
    ResponseEntity<?>editPregunta(@Validated @RequestBody PreguntaDto preguntaDto,@PathVariable Long preguntaid){

        return new ResponseEntity<>(preguntaService.editPregunta(preguntaDto,preguntaid),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{preguntaId}")
        ResponseEntity<?>eliminarPregunta(@PathVariable Long preguntaId){
        preguntaService.deletePregunta(preguntaId);
        Map<String,Object> resp=new HashMap<>();
        resp.put("mensaje","LA PREGUNTA CON EL ID: "+preguntaId+" FUE ELIMINADO!");
        return ResponseEntity.ok(resp);
        }

    @PostMapping("/examen-evaluado")
    ResponseEntity<?> evalularExamen(@RequestBody List<Pregunta> preguntas){
        return ResponseEntity.ok(preguntaService.evaluarExamen(preguntas));
    }
    }



