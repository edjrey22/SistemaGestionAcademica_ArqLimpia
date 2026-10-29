package com.academico.application.validation.curso;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Curso;

// Eslabón: el ID del curso debe ser un número positivo
public class ValidadorIdCurso extends Validador<Curso> {

    @Override
    protected void verificar(Curso c) {
        if (c.getId() <= 0) {
            throw new ValidacionException(
                    "[CHAIN-Id] El ID del curso debe ser un número positivo.");
        }
    }
}
