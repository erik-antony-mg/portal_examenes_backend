package com.sistema.examenes.repositories;


import com.sistema.examenes.entities.Examen;
import com.sistema.examenes.entities.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {

    Set<Pregunta> findByExamen(Examen examen);
    Set<Pregunta> findAllByExamen_ExamenId(Long examenId);

}
