package com.academico.infrastructure.adapter.out.audit;

import com.academico.application.event.AcademicoObserver;
import com.academico.application.event.EventoAcademico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

/** Observador concreto: deja constancia de cada evento en data/auditoria.log. */
public class AuditoriaObserver implements AcademicoObserver {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Path archivo = Path.of("data", "auditoria.log");

    @Override
    public void actualizar(EventoAcademico evento) {
        String linea = String.format("%s | %-11s | %-10s | id=%d | %s%n",
                evento.fecha().format(FORMATO), evento.tipo(), evento.entidad(),
                evento.id(), evento.descripcion());
        try {
            Files.createDirectories(archivo.getParent());
            Files.writeString(archivo, linea,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("[OBSERVER-Auditoría] Evento guardado en " + archivo);
        } catch (IOException ex) {
            System.out.println("[OBSERVER-Auditoría] Error: " + ex.getMessage());
        }
    }
}
