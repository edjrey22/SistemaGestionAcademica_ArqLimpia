package com.academico.application.event;

import java.time.LocalDateTime;

/** Información que el publicador envía a los observadores. */
public record EventoAcademico(TipoEvento tipo, String entidad, int id,
                              String descripcion, LocalDateTime fecha) {

    public EventoAcademico(TipoEvento tipo, String entidad, int id, String descripcion) {
        this(tipo, entidad, id, descripcion, LocalDateTime.now());
    }
}
