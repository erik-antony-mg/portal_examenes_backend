package com.sistema.examenes.controllers;

import com.sistema.examenes.entities.dto.CategoriaDto;
import com.sistema.examenes.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping("/create")
    ResponseEntity<?> createCategoria(@Validated @RequestBody CategoriaDto categoriaDto){
        return new ResponseEntity<>(categoriaService.createCategoria(categoriaDto), HttpStatus.CREATED);
    }
    @GetMapping("/{idCtegoria}")
    ResponseEntity<?> getCategoriById(@PathVariable Long idCtegoria){
        return ResponseEntity.ok(categoriaService.getCategoria(idCtegoria));
    }
    @GetMapping
    ResponseEntity<?> getAllCategorias(){
        return ResponseEntity.ok(categoriaService.getAllCategoria());
    }

    @PutMapping("/edit/{idCategoria}")
    ResponseEntity<?> editCategoria(@Validated @RequestBody CategoriaDto categoriaDto
            ,@PathVariable Long idCategoria){
        return ResponseEntity.ok(categoriaService.actualizarCategoria(categoriaDto,idCategoria));
    }

    @DeleteMapping("/delete/{idCtegoria}")
    ResponseEntity<?> deleteCategoria(@PathVariable Long idCtegoria){
        categoriaService.deleteCategoria(idCtegoria);
        Map<String,Object> resp=new HashMap<>();
        resp.put("mensaje","LA CATEGORIA CON EL ID: "+idCtegoria+" FUE ELIMINADA!");
        return ResponseEntity.ok(resp);
    }
}
