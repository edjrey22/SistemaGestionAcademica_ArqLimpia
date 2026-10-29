package com.academico.infrastructure.adapter.out.persistence.decorator;

import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** Decorador concreto: añade registro de operaciones y tiempo de respuesta. */
public class EstudianteRepositoryLog extends EstudianteRepositoryDecorator {

    private static final DateTimeFormatter HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public EstudianteRepositoryLog(EstudianteRepository envuelto) {
        super(envuelto);
    }

    @Override
    public void guardar(Estudiante e) {
        long inicio = System.nanoTime();
        super.guardar(e);                                          // original
        registrar("guardar(id=" + e.getId() + ")", "OK", inicio); // añadido
    }

    @Override
    public List<Estudiante> listar() {
        long inicio = System.nanoTime();
        List<Estudiante> lista = super.listar();
        registrar("listar()", lista.size() + " registros", inicio);
        return lista;
    }

    @Override
    public boolean actualizar(Estudiante e) {
        long inicio = System.nanoTime();
        boolean ok = super.actualizar(e);
        registrar("actualizar(id=" + e.getId() + ")", ok ? "OK" : "NO ENCONTRADO", inicio);
        return ok;
    }

    @Override
    public boolean eliminar(int id) {
        long inicio = System.nanoTime();
        boolean ok = super.eliminar(id);
        registrar("eliminar(id=" + id + ")", ok ? "OK" : "NO ENCONTRADO", inicio);
        return ok;
    }

    private void registrar(String operacion, String resultado, long inicio) {
        long ms = (System.nanoTime() - inicio) / 1_000_000;
        System.out.printf("[LOG %s] Estudiantes.%s -> %s (%d ms)%n",
                LocalTime.now().format(HORA), operacion, resultado, ms);
    }
}
