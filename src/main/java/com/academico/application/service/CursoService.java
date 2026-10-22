package com.academico.application;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;
import com.academico.application.port.in.CursoUseCase;

import java.util.List;

public class CursoService implements CursoUseCase {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }
    @Override
    public boolean registrar(Curso curso) {

        List<Curso> cursos = repository.listar();

        boolean existe = cursos.stream()
                .anyMatch(c -> c.getId() == curso.getId());

        if (existe) {
            return false;
        }

        cursos.add(curso);

        repository.guardar(cursos);

        return true;
    }
    @Override
    public List<Curso> listar() {
        return repository.listar();
    }
    @Override
    public boolean actualizar(Curso curso) {

        List<Curso> cursos = repository.listar();

        for (Curso c : cursos) {

            if (c.getId() == curso.getId()) {

                c.setNombre(curso.getNombre());
                c.setCreditos(curso.getCreditos());

                repository.guardar(cursos);

                return true;
            }
        }

        return false;
    }
    @Override
    public boolean eliminar(int id) {

        List<Curso> cursos = repository.listar();

        boolean eliminado = cursos.removeIf(c -> c.getId() == id);

        if (eliminado) {
            repository.guardar(cursos);
        }

        return eliminado;
    }
}
