package com.academico.application.port.in;

import com.academico.domain.model.Estudiante;

import java.util.List;

// Puerto de entrada: operaciones disponibles para los adaptadores de entrada.
public interface EstudianteUseCase {
    boolean registrar(Estudiante estudiante);
    List<Estudiante> listar();
    boolean actualizar(Estudiante estudiante);
    boolean eliminar(int id);
}
