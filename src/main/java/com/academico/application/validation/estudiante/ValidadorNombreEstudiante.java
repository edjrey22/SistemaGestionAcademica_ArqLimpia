package com.academico.application.validation.estudiante;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Estudiante;

// Eslabón: el nombre debe tener al menos 3 letras y solo letras o espacios
public class ValidadorNombreEstudiante extends Validador<Estudiante> {

    @Override
    protected void verificar(Estudiante e) {
        String nombre = e.getNombre() == null ? "" : e.getNombre().trim();
        if (nombre.length() < 3 || !nombre.matches("[\\p{L} ]+")) {
            throw new ValidacionException(
                    "[CHAIN-Nombre] El nombre debe tener al menos 3 letras y solo letras.");
        }
    }
}
