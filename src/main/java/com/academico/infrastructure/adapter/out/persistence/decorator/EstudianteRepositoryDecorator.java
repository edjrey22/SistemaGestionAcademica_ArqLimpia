package com.academico.infrastructure.adapter.out.persistence.decorator;

import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;

import java.util.List;
import java.util.Optional;

/** Decorador base: implementa el puerto y delega todo en el objeto envuelto. */
public abstract class EstudianteRepositoryDecorator implements EstudianteRepository {

    protected final EstudianteRepository envuelto;

    protected EstudianteRepositoryDecorator(EstudianteRepository envuelto) {
        this.envuelto = envuelto;
    }

    @Override
    public void guardar(Estudiante e) { envuelto.guardar(e); }

    @Override
    public List<Estudiante> listar() { return envuelto.listar(); }

    @Override
    public Optional<Estudiante> buscarPorId(int id) { return envuelto.buscarPorId(id); }

    @Override
    public boolean actualizar(Estudiante e) { return envuelto.actualizar(e); }

    @Override
    public boolean eliminar(int id) { return envuelto.eliminar(id); }
}
