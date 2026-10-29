package com.academico.application.validation;

// Error de una regla de negocio; la interfaz lo muestra como "Error: ..."
public class ValidacionException extends IllegalArgumentException {

    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
