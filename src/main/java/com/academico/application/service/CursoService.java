package com.academico.application.service;

import com.academico.application.event.AcademicoObserver;
import com.academico.application.event.EventoAcademico;
import com.academico.application.event.PublicadorEventos;
import com.academico.application.event.TipoEvento;
import com.academico.application.port.in.CursoUseCase;
import com.academico.application.validation.Validador;
import com.academico.application.validation.curso.ValidadorCreditosCurso;
import com.academico.application.validation.curso.ValidadorCursoDuplicado;
import com.academico.application.validation.curso.ValidadorIdCurso;
import com.academico.application.validation.curso.ValidadorNombreCurso;
import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

public class CursoService implements CursoUseCase {

    private final CursoRepository repository;
    // Observer: el servicio publica un evento tras cada operación exitosa
    private final PublicadorEventos publicador = new PublicadorEventos();
    // Chain of Responsibility: validaciones antes de registrar y actualizar
    private final Validador<Curso> cadenaRegistro;
    private final Validador<Curso> cadenaActualizacion;

    public CursoService(CursoRepository repository) {
        this.repository = repository;

        // Registro: Id -> Nombre -> Créditos -> Duplicado
        this.cadenaRegistro = new ValidadorIdCurso();
        cadenaRegistro.enlazar(new ValidadorNombreCurso())
                      .enlazar(new ValidadorCreditosCurso())
                      .enlazar(new ValidadorCursoDuplicado(repository));

        // Actualización: Nombre -> Créditos (el ID ya existe)
        this.cadenaActualizacion = new ValidadorNombreCurso();
        cadenaActualizacion.enlazar(new ValidadorCreditosCurso());
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
        cadenaRegistro.validar(curso);   // incluye el control de ID duplicado
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
        cadenaActualizacion.validar(curso);
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
