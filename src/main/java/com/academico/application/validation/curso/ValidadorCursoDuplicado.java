package com.academico.application.validation.curso;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

// Eslabón: el ID del curso no debe estar registrado
public class ValidadorCursoDuplicado extends Validador<Curso> {

    private final CursoRepository repository;

    public ValidadorCursoDuplicado(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void verificar(Curso c) {
        if (repository.buscarPorId(c.getId()).isPresent()) {
            throw new ValidacionException(
                    "[CHAIN-Duplicado] Ya existe un curso con el ID " + c.getId() + ".");
        }
    }
}
