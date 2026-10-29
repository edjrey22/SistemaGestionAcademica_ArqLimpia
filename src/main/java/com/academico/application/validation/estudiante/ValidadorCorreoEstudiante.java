package com.academico.application.validation.estudiante;

import com.academico.application.validation.ValidacionException;
import com.academico.application.validation.Validador;
import com.academico.domain.model.Estudiante;

import java.util.regex.Pattern;

// Eslabón: el correo debe tener un formato válido (usuario@dominio.ext)
public class ValidadorCorreoEstudiante extends Validador<Estudiante> {

    private static final Pattern FORMATO =
            Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    @Override
    protected void verificar(Estudiante e) {
        if (e.getCorreo() == null || !FORMATO.matcher(e.getCorreo().trim()).matches()) {
            throw new ValidacionException(
                    "[CHAIN-Correo] El correo no tiene un formato válido.");
        }
    }
}
