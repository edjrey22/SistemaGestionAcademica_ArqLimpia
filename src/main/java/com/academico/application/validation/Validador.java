package com.academico.application.validation;

/**
 * Chain of Responsibility: cada eslabón valida una regla y pasa la petición
 * al siguiente. Se reutiliza para estudiantes y cursos con el tipo T.
 */
public abstract class Validador<T> {

    private Validador<T> siguiente;

    // Devuelve el eslabón enlazado para poder encadenar llamadas
    public Validador<T> enlazar(Validador<T> siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public void validar(T objeto) {
        verificar(objeto);   // si la regla falla, lanza excepción y corta la cadena
        if (siguiente != null) {
            siguiente.validar(objeto);
        }
    }

    protected abstract void verificar(T objeto);
}
