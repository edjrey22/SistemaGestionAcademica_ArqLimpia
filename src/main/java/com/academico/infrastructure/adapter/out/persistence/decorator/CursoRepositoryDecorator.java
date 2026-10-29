package com.academico.infrastructure.adapter.out.persistence.decorator;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

/** Decorador base: implementa el puerto de cursos y delega en el objeto envuelto. */
public abstract class CursoRepositoryDecorator implements CursoRepository {

    protected final CursoRepository envuelto;

    protected CursoRepositoryDecorator(CursoRepository envuelto) {
        this.envuelto = envuelto;
    }

    @Override
    public void guardar(Curso c) { envuelto.guardar(c); }

    @Override
    public List<Curso> listar() { return envuelto.listar(); }

    @Override
    public Optional<Curso> buscarPorId(int id) { return envuelto.buscarPorId(id); }

    @Override
    public boolean actualizar(Curso c) { return envuelto.actualizar(c); }

    @Override
    public boolean eliminar(int id) { return envuelto.eliminar(id); }
}
