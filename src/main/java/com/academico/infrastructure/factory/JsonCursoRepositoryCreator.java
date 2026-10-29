package com.academico.infrastructure.factory;

import com.academico.domain.repository.CursoRepository;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryJson;

// Creador concreto: produce el repositorio de cursos en archivo JSON
public class JsonCursoRepositoryCreator extends CursoRepositoryCreator {

    @Override
    protected CursoRepository crearRepositorio() {
        return new CursoRepositoryJson();
    }
}
