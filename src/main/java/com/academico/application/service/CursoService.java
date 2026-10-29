package com.academico.application.service;

import com.academico.application.event.AcademicoObserver;
import com.academico.application.event.EventoAcademico;
import com.academico.application.event.PublicadorEventos;
import com.academico.application.event.TipoEvento;
import com.academico.application.port.in.CursoUseCase;
import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

public class CursoService implements CursoUseCase {

    private final CursoRepository repository;
    // Observer: el servicio publica un evento tras cada operación exitosa
    private final PublicadorEventos publicador = new PublicadorEventos();

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public void suscribir(AcademicoObserver observador) {
        publicador.suscribir(observador);
    }

    private void notificar(TipoEvento tipo, Curso curso) {
        publicador.notificar(new EventoAcademico(
                tipo, "Curso", curso.getId(), curso.getNombre()));
    }

    @Override
    public boolean registrar(Curso curso) {
        if (repository.buscarPorId(curso.getId()).isPresent()) {
            return false;
        }
        repository.guardar(curso);
        notificar(TipoEvento.REGISTRADO, curso);
        return true;
    }

    @Override
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    public boolean actualizar(Curso curso) {
        boolean actualizado = repository.actualizar(curso);
        if (actualizado) {
            notificar(TipoEvento.ACTUALIZADO, curso);
        }
        return actualizado;
    }

    @Override
    public boolean eliminar(int id) {
        Optional<Curso> existente = repository.buscarPorId(id);
        boolean eliminado = repository.eliminar(id);
        if (eliminado) {
            existente.ifPresent(c -> notificar(TipoEvento.ELIMINADO, c));
        }
        return eliminado;
    }
}
