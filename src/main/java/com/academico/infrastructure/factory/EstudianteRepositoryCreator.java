package com.academico.infrastructure.factory;

import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.decorator.EstudianteRepositoryLog;

/**
 * Factory Method: el creador define el flujo para preparar el repositorio
 * y deja que cada subclase decida qué repositorio concreto se crea.
 */
public abstract class EstudianteRepositoryCreator {

    // Operación del creador: usa el producto sin conocer su clase concreta
    public EstudianteRepository prepararRepositorio() {
        EstudianteRepository repositorio = crearRepositorio();
        System.out.println("[FACTORY METHOD] " + getClass().getSimpleName()
                + " creó " + repositorio.getClass().getSimpleName());
        // Decorator: se añade el registro de operaciones sin tocar el adaptador
        return new EstudianteRepositoryLog(repositorio);
    }

    // Método fábrica: lo implementa cada creador concreto
    protected abstract EstudianteRepository crearRepositorio();
}
