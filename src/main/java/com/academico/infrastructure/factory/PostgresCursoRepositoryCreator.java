package com.academico.infrastructure.factory;

import com.academico.domain.repository.CursoRepository;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryPostgres;

// Creador concreto: produce el repositorio de cursos en PostgreSQL
public class PostgresCursoRepositoryCreator extends CursoRepositoryCreator {

    @Override
    protected CursoRepository crearRepositorio() {
        return new CursoRepositoryPostgres();
    }
}
