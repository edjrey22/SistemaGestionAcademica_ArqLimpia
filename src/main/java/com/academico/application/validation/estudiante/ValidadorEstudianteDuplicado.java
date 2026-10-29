package com.academico.application.validation.estudiante;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;

// Eslabón: el ID no debe estar registrado (aplica a JSON y PostgreSQL)
public class ValidadorEstudianteDuplicado extends Validador<Estudiante> {

    private final EstudianteRepository repository;

    public ValidadorEstudianteDuplicado(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void verificar(Estudiante e) {
        if (repository.buscarPorId(e.getId()).isPresent()) {
            throw new ValidacionException(
                    "[CHAIN-Duplicado] Ya existe un estudiante con el ID " + e.getId() + ".");
        }
    }
}
