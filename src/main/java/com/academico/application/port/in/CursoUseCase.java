package com.academico.application.port.in;

import com.academico.domain.model.Curso;

import java.util.List;

// Puerto de entrada: operaciones disponibles para los adaptadores de entrada.
public interface CursoUseCase {
    boolean registrar(Curso curso);
    List<Curso> listar();
    boolean actualizar(Curso curso);
    boolean eliminar(int id);
}