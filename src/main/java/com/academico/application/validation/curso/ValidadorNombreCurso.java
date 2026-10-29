package com.academico.application.validation.curso;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Curso;

// Eslabón: el nombre del curso debe tener al menos 3 caracteres (letras, números o espacios)
public class ValidadorNombreCurso extends Validador<Curso> {

    @Override
    protected void verificar(Curso c) {
        String nombre = c.getNombre() == null ? "" : c.getNombre().trim();
        if (nombre.length() < 3 || !nombre.matches("[\\p{L}\\p{N} ]+")) {
            throw new ValidacionException(
                    "[CHAIN-Nombre] El nombre del curso debe tener al menos 3 caracteres válidos.");
        }
    }
}
