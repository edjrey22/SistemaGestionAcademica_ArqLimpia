package com.academico.application.event;

import java.util.ArrayList;
import java.util.List;

/** Publicador: mantiene la lista de observadores y les envía cada evento. */
public class PublicadorEventos {

    private final List<AcademicoObserver> observadores = new ArrayList<>();

    public void suscribir(AcademicoObserver o)   { observadores.add(o); }
    public void desuscribir(AcademicoObserver o) { observadores.remove(o); }

    public void notificar(EventoAcademico evento) {
        observadores.forEach(o -> o.actualizar(evento));
    }
}
