package com.academico.application.validation.estudiante;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Estudiante;

// Eslabón: el ID debe ser un número positivo
public class ValidadorIdEstudiante extends Validador<Estudiante> {

    @Override
    protected void verificar(Estudiante e) {
        if (e.getId() <= 0) {
            throw new ValidacionException(
                    "[CHAIN-Id] El ID debe ser un número positivo.");
        }
    }
}
