package com.academico.application.validation.curso;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Curso;

// Eslabón: los créditos deben estar entre 1 y 10
public class ValidadorCreditosCurso extends Validador<Curso> {

    private static final int MINIMO = 1;
    private static final int MAXIMO = 10;

    @Override
    protected void verificar(Curso c) {
        if (c.getCreditos() < MINIMO || c.getCreditos() > MAXIMO) {
            throw new ValidacionException(
                    "[CHAIN-Créditos] Los créditos deben estar entre "
                            + MINIMO + " y " + MAXIMO + ".");
        }
    }
}
