package com.academico.infrastructure.factory;

import com.academico.domain.repository.CursoRepository;
import com.academico.infrastructure.adapter.out.persistence.decorator.CursoRepositoryLog;

/**
 * Factory Method: el creador define el flujo para preparar el repositorio
 * de cursos y deja que cada subclase decida qué repositorio concreto se crea.
 */
public abstract class CursoRepositoryCreator {

    // Operación del creador: usa el producto sin conocer su clase concreta
    public CursoRepository prepararRepositorio() {
        CursoRepository repositorio = crearRepositorio();
        System.out.println("[FACTORY METHOD] " + getClass().getSimpleName()
                + " creó " + repositorio.getClass().getSimpleName());
        // Decorator: se añade el registro de operaciones sin tocar el adaptador
        return new CursoRepositoryLog(repositorio);
    }

    // Método fábrica: lo implementa cada creador concreto
    protected abstract CursoRepository crearRepositorio();
}
