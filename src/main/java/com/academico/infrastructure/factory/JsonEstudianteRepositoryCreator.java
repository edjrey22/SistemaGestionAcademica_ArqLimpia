package com.academico.infrastructure.factory;

import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryJson;

// Creador concreto: produce el repositorio de estudiantes en archivo JSON
public class JsonEstudianteRepositoryCreator extends EstudianteRepositoryCreator {

    @Override
    protected EstudianteRepository crearRepositorio() {
        return new EstudianteRepositoryJson();
    }
}
