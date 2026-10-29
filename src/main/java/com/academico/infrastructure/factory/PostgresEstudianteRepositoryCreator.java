package com.academico.infrastructure.factory;

import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryPostgres;

// Creador concreto: produce el repositorio de estudiantes en PostgreSQL
public class PostgresEstudianteRepositoryCreator extends EstudianteRepositoryCreator {

    @Override
    protected EstudianteRepository crearRepositorio() {
        return new EstudianteRepositoryPostgres();
    }
}
