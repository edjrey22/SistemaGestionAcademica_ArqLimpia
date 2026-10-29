package com.academico.infrastructure.adapter.out.persistence.proxy;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Proxy de caché: controla el acceso al repositorio real de cursos. */
public class CursoRepositoryCacheProxy implements CursoRepository {

    private final CursoRepository real;
    private List<Curso> cache;                    // null = caché vacía

    public CursoRepositoryCacheProxy(CursoRepository real) {
        this.real = real;
    }

    @Override
    public List<Curso> listar() {
        if (cache == null) {
            System.out.println(
                    "[PROXY] Cursos: caché vacía, consultando el almacenamiento real...");
            cache = real.listar();
        } else {
            System.out.println(
                    "[PROXY] Cursos: " + cache.size() + " registros devueltos desde caché");
        }
        return new ArrayList<>(cache);   // copia: el cliente no altera la caché
    }

    @Override
    public Optional<Curso> buscarPorId(int id) {
        return listar().stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public void guardar(Curso c) {
        real.guardar(c);
        invalidar();
    }

    @Override
    public boolean actualizar(Curso c) {
        boolean ok = real.actualizar(c);
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
        System.out.println("[PROXY] Cursos: datos modificados, caché invalidada");
    }
}
