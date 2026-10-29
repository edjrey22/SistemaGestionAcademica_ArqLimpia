package com.academico.presentation;

import com.academico.application.event.AcademicoObserver;
import com.academico.application.event.EventoAcademico;

/** Observador concreto: avisa al usuario en la consola. */
public class ConsolaObserver implements AcademicoObserver {

    @Override
    public void actualizar(EventoAcademico evento) {
        System.out.println("[OBSERVER-Consola] " + evento.entidad() + " "
                + evento.tipo().name().toLowerCase() + ": " + evento.descripcion());
    }
}
