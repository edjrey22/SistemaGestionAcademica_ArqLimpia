package com.academico.infrastructure.adapter.out.persistence.proxy;

import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Proxy de caché: controla el acceso al repositorio real y evita relecturas. */
public class EstudianteRepositoryCacheProxy implements EstudianteRepository {

    private final EstudianteRepository real;
    private List<Estudiante> cache;               // null = caché vacía

    public EstudianteRepositoryCacheProxy(EstudianteRepository real) {
        this.real = real;
    }

    @Override
    public List<Estudiante> listar() {
        if (cache == null) {
            System.out.println(
                    "[PROXY] Estudiantes: caché vacía, consultando el almacenamiento real...");
            cache = real.listar();
        } else {
            System.out.println(
                    "[PROXY] Estudiantes: " + cache.size() + " registros devueltos desde caché");
        }
        return new ArrayList<>(cache);   // copia: el cliente no altera la caché
    }

    @Override
    public Optional<Estudiante> buscarPorId(int id) {
        return listar().stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public void guardar(Estudiante e) {
        real.guardar(e);
        invalidar();
    }

    @Override
    public boolean actualizar(Estudiante e) {
        boolean ok = real.actualizar(e);
        if (ok) {
            invalidar();
        }
        return ok;
    }

    @Override
    public boolean eliminar(int id) {
        boolean ok = real.eliminar(id);
        if (ok) {
            invalidar();
        }
        return ok;
    }

    private void invalidar() {
        cache = null;
        System.out.println("[PROXY] Estudiantes: datos modificados, caché invalidada");
    }
}
