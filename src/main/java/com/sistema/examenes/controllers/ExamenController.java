package com.sistema.examenes.controllers;

import com.sistema.examenes.entities.dto.ExamenDto;
import com.sistema.examenes.services.ExamenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/examen")
@AllArgsConstructor
@RestController
public class ExamenController {

    private ExamenService examenService;

    @GetMapping
    ResponseEntity<?> getAllExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }
    @GetMapping("{examenId}")
    ResponseEntity<?> getExamen(@PathVariable Long examenId){
        return ResponseEntity.ok(examenService.obtenerExamen(examenId));
    }
//    @GetMapping("/listaPreguntas/{examenId}")
//    ResponseEntity<?> listaDePreguntasDelExamen(@PathVariable Long examenId){
//return examenService.;
//    }
    @PostMapping("/create")
    ResponseEntity<?> createExamen(@Validated @RequestBody ExamenDto examenDto){
        return new ResponseEntity<>(examenService.createExamne(examenDto), HttpStatus.CREATED);
    }
    @PutMapping("/edit/{examenId}")
    ResponseEntity<?> editExamen(@Validated @RequestBody ExamenDto examenDto,@PathVariable Long examenId) {
    return ResponseEntity.ok(examenService.actualizarExamen(examenDto,examenId));
    }
    @DeleteMapping("/delete/{examenId}")
    ResponseEntity<?> deleteExamen(@PathVariable Long examenId){
        examenService.eliminarExamen(examenId);
        Map<String,Object> resp=new HashMap<>();
        resp.put("mensaje","EL EXAMEN CON EL ID: "+examenId+" FUE ELIMINADO!");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/categoria/{categoriaId}")
    ResponseEntity<?> getAllCategoriaById(@PathVariable Long categoriaId){
        return ResponseEntity.ok(examenService.getAllExamByCategoriaId(categoriaId));
    }
    @GetMapping("/activos")
    ResponseEntity<?> getAllExamenesEnable(){
        return ResponseEntity.ok(examenService.getAllExamensEnable(true));
    }
    @GetMapping("/categoria/activos/{categoriaId}")
    ResponseEntity<?>getAllExamenesEnableByCategoria(@PathVariable Long categoriaId){
        return ResponseEntity.ok(examenService.getAllExamsByCategoriaIdEnable(true,categoriaId));
    }
}
