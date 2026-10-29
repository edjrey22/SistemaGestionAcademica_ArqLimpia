package com.academico.application.event;

/** Observer: todo suscriptor recibe los eventos de estudiantes y cursos. */
public interface AcademicoObserver {
    void actualizar(EventoAcademico evento);
}
