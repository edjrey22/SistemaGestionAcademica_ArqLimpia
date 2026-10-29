package com.academico.infrastructure.adapter.out.persistence.decorator;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** Decorador concreto: añade registro de operaciones de cursos y su duración. */
public class CursoRepositoryLog extends CursoRepositoryDecorator {

    private static final DateTimeFormatter HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public CursoRepositoryLog(CursoRepository envuelto) {
        super(envuelto);
    }

    @Override
    public void guardar(Curso c) {
        long inicio = System.nanoTime();
        super.guardar(c);                                          // original
        registrar("guardar(id=" + c.getId() + ")", "OK", inicio); // añadido
    }

    @Override
    public List<Curso> listar() {
        long inicio = System.nanoTime();
        List<Curso> lista = super.listar();
        registrar("listar()", lista.size() + " registros", inicio);
        return lista;
    }

    @Override
    public boolean actualizar(Curso c) {
        long inicio = System.nanoTime();
        boolean ok = super.actualizar(c);
        registrar("actualizar(id=" + c.getId() + ")", ok ? "OK" : "NO ENCONTRADO", inicio);
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
        System.out.printf("[LOG %s] Cursos.%s -> %s (%d ms)%n",
                LocalTime.now().format(HORA), operacion, resultado, ms);
    }
}
