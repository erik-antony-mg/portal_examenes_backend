package com.sistema.examenes.repositories;


import com.sistema.examenes.entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Set;

@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {

    Set<Examen> findAllByCategoriaCategoriaId(Long idCatepgora);
    Set<Examen> findAllByEnable(Boolean estado);
    Set<Examen> findAllByEnableAndCategoria_CategoriaId(Boolean estado, Long categoriaId);
}
